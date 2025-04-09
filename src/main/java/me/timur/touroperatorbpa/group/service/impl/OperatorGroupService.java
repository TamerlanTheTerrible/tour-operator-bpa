package me.timur.touroperatorbpa.group.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.timur.touroperatorbpa.application.model.ApplicationDto;
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
import me.timur.touroperatorbpa.model.enums.Role;
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

    /**
     * This service is responsible for managing groups for tour operators.
     * It provides methods to create, update, cancel and fetch groups.
     */

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
        Group group = saveAndNotify(new Group(createDto, currentUser, company), "Created group");
        log.info("Created group with id: {} and number: {}", group.getId(), group.getNumber());

        return new GroupDto(group);
    }

    private void validateGroupNumber(String number, User currentUser) {
        if (number != null && groupRepository.existsByNumberAndTourOperator_UserCompany(number, currentUser.getUserCompany())) {
            throw new ClientException(ResponseCode.BAD_REQUEST, "Group number is not unique: " + number);
        }
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

        saveAndNotify(group, "Group updated");

        return new GroupDto(group, getApplicationMap(group));
    }

    @Override
    public void cancel(Long id) {
        log.info("Cancelling group with id: {}", id);
        var group = getGroup(id);
        try {
            Arrays.stream(ApplicationType.values()).forEach(applicationType -> {
                var applicationService = applicationServiceFactory.getByType(applicationType);
                if (applicationService != null) {
                    applicationService.cancelByGroupId(group.getId());
                }
            });
            group.setStatus(GroupStatus.CANCELLED);
            saveAndNotify(group, "Group cancelled");
        } catch (Exception e) {
            log.error("Error while cancelling group with id: {}", id, e);
            sendNotification(group, "Error while cancelling group");
            throw new RuntimeException(e);
        }
    }

    @Override
    public GroupDto get(Long id) {
        return new GroupDto(getGroup(id), getApplicationMap(getGroup(id)));
    }

    @Override
    public PageableList<GroupDto> getAllByFiltered(GroupFilter filter) {
        if (filter.getTourOperatorId() == null) {
            final User user = UserContext.getUser();
            if(user.getRoleNames().contains(Role.TOUR_MANAGER)) {
                filter.setTourOperatorId(user.getId());
            } else {
                filter.setCompanyId(user.getUserCompany().getId());
            }
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


    private Group saveAndNotify(Group entity, String message) {
        sendNotification(entity, message);
        return groupRepository.save(entity);
    }

    private void sendNotification(Group group, String message) {
        try {
            notificationService.create(NotificationCreateDto.builder()
                    .groupId(group.getId())
                    .message(String.format(message + "%s.\nId: %s, number: %s", message, group.getId(), group.getNumber()))
                    .build());
        } catch (Exception e) {
            log.error("Error while sending notification", e);
        }
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

}