package me.timur.touroperatorbpa.group.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.timur.touroperatorbpa.application.model.ApplicationDto;
import me.timur.touroperatorbpa.application.service.ApplicationService;
import me.timur.touroperatorbpa.application.service.ApplicationServiceFactory;
import me.timur.touroperatorbpa.domain.entity.Group;
import me.timur.touroperatorbpa.domain.entity.PartnerCompany;
import me.timur.touroperatorbpa.domain.entity.User;
import me.timur.touroperatorbpa.domain.repository.CompanyRepository;
import me.timur.touroperatorbpa.domain.repository.GroupRepository;
import me.timur.touroperatorbpa.domain.repository.UserRepository;
import me.timur.touroperatorbpa.domain.repository.impl.GroupFilteredFetchRepository;
import me.timur.touroperatorbpa.exception.ClientException;
import me.timur.touroperatorbpa.group.model.GroupCreateDto;
import me.timur.touroperatorbpa.group.model.GroupDto;
import me.timur.touroperatorbpa.group.model.GroupFilter;
import me.timur.touroperatorbpa.group.service.GroupService;
import me.timur.touroperatorbpa.model.PageableList;
import me.timur.touroperatorbpa.model.enums.ApplicationType;
import me.timur.touroperatorbpa.model.enums.GroupStatus;
import me.timur.touroperatorbpa.model.enums.ResponseCode;
import me.timur.touroperatorbpa.notification.model.NotificationCreateDto;
import me.timur.touroperatorbpa.notification.service.NotificationService;
import me.timur.touroperatorbpa.security.UserContext;
import me.timur.touroperatorbpa.util.CommentUtil;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Temurbek Ismoilov on 28/07/23.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class OperatorGroupService implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupFilteredFetchRepository groupCustomRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final NotificationService notificationService;
    private final ApplicationServiceFactory applicationServiceFactory;

    @Override
    public GroupDto create(@Valid GroupCreateDto createDto) {
        log.info("Creating group: {}", createDto);
        // Prepare entities k
        var company = createDto.getCompanyId() != null ? getCompany(createDto.getCompanyId()) : null;
        var currentUser = UserContext.getUser();

        // Check if group number is unique
        validateGroupNumber(createDto.getNumber(), currentUser);

        // Save and return group
        Group group = saveGroup(createDto, currentUser, company);
        log.info("Created group with id: {} and number: {}", group.getId(), group.getNumber());

        // Send notification
        sendNotification(group);

        return new GroupDto(group);
    }

    private void validateGroupNumber(String number, User currentUser) {
        if (number != null && groupRepository.existsByNumberAndTourOperator_UserCompany(number, currentUser.getUserCompany())) {
            throw new ClientException(ResponseCode.BAD_REQUEST, "Group number is not unique: " + number);
        }
    }

    private Group saveGroup(GroupCreateDto createDto, User currentUser, PartnerCompany company) {
        return groupRepository.save(new Group(createDto, currentUser, company));
    }

    private void sendNotification(Group group) {
        notificationService.create(NotificationCreateDto.builder()
            .groupId(group.getId())
            .message(String.format("Created group with id: %s and number: %s", group.getId(), group.getNumber()))
            .build());
    }

    @Override
    public GroupDto update(GroupDto dto) {
        log.info("Updating group: {}", dto);

        //update group
        var group = getGroup(dto.getId());
        if (dto.getNumber() != null) {
            group.setNumber(dto.getNumber());
        }
        if (dto.getTourOperatorId() != null) {
            group.setTourOperator(
                    getUser(dto.getTourOperatorId())
            );
        }
        if (dto.getCountry() != null) {
            group.setCountry(dto.getCountry());
        }
        if (dto.getCompanyId() != null) {
            group.setPartnerCompany(
                    getCompany(dto.getCompanyId())
            );
        }
        if (dto.getSize() != null) {
            group.setSize(dto.getSize());
        }
        if (dto.getTourLeaderCount() != null) {
            group.setTourLeaderCount(dto.getTourLeaderCount());
        }
        if (dto.getArrival() != null) {
            group.setArrivalTime(dto.getArrival());
        }
        if (dto.getDeparture() != null) {
            group.setDepartureTime(dto.getDeparture());
        }

        if (dto.getComment() != null) {
            group.setComment(CommentUtil.appendComment(group.getComment(), dto.getComment(), UserContext.getUser().getUsername()));
        }

        groupRepository.save(group);

        return new GroupDto(group, getApplicationMap(group));
    }

    private Map<ApplicationType, ApplicationDto> getApplicationMap(Group group) {
        HashMap<ApplicationType, ApplicationDto> applicationDtoMap = new HashMap<>();
        User user = UserContext.getUser();
        Arrays.stream(ApplicationType.values()).forEach(applicationType -> {
            var applicationService = applicationServiceFactory.getByType(applicationType);
            if(applicationService != null) {
                var application = applicationService.getByGroupId(group.getId(), user);
                if (application != null) {
                    applicationDtoMap.put(applicationType, application);
                }
            }
        });
        return applicationDtoMap;
    }

    @Override
    public void cancel(Long id) {
        log.info("Cancelling group with id: {}", id);

        var group = getGroup(id);
        group.setStatus(GroupStatus.CANCELLED);
        groupRepository.save(group);

        log.info("Cancelling applications of group with id: {}", group.getId());
        //TODO: cancel applications
    }

    @Override
    public GroupDto get(Long id) {
        return new GroupDto(getGroup(id));
    }

    @Override
    public PageableList<GroupDto> getAllByFiltered(GroupFilter filter) {
        if (filter.getTourOperatorId() == null && UserContext.getUser().getRoleNames().contains("TOUR_OPERATOR")) {
            filter.setTourOperatorId(UserContext.getUser().getId());
        }

        final Pair<List<Group>, Long> result = groupCustomRepository.findAllFiltered(filter);

        return new PageableList<>(
                result.getSecond(),
                result.getFirst().stream().map(GroupDto::new).toList()
        );
    }

    private Group getGroup(Long id) {
        var group = groupRepository.findById(id)
                .orElseThrow(() -> new ClientException(ResponseCode.RESOURCE_NOT_FOUND, "Could not find group with id: " + id));

        if (!Objects.equals(group.getTourOperator().getId(), UserContext.getUser().getId())) {
            throw new ClientException(ResponseCode.FORBIDDEN_RESOURCE, String.format("User %s is not allowed to access group %s", UserContext.getUser().getId(), id));
        }

        return group;
    }

    private User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ClientException(ResponseCode.RESOURCE_NOT_FOUND, "Could not find user with id: " + id));
    }

    private PartnerCompany getCompany(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new ClientException(ResponseCode.RESOURCE_NOT_FOUND, "Could not find company with id: " + id));
    }
}