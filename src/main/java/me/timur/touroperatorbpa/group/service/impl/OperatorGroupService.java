package me.timur.touroperatorbpa.group.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.timur.touroperatorbpa.domain.entity.PartnerCompany;
import me.timur.touroperatorbpa.domain.entity.Group;
import me.timur.touroperatorbpa.domain.entity.User;
import me.timur.touroperatorbpa.group.service.GroupService;
import me.timur.touroperatorbpa.model.enums.GroupStatus;
import me.timur.touroperatorbpa.domain.repository.CompanyRepository;
import me.timur.touroperatorbpa.domain.repository.GroupRepository;
import me.timur.touroperatorbpa.domain.repository.UserRepository;
import me.timur.touroperatorbpa.domain.repository.impl.GroupFilteredFetchRepository;
import me.timur.touroperatorbpa.exception.ClientException;
import me.timur.touroperatorbpa.model.enums.ResponseCode;
import me.timur.touroperatorbpa.model.PageableList;
import me.timur.touroperatorbpa.group.model.GroupCreateDto;
import me.timur.touroperatorbpa.group.model.GroupDto;
import me.timur.touroperatorbpa.group.model.GroupFilter;
import me.timur.touroperatorbpa.model.enums.Role;
import me.timur.touroperatorbpa.notification.model.NotificationCreateDto;
import me.timur.touroperatorbpa.notification.service.NotificationService;
import me.timur.touroperatorbpa.security.UserContext;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    @Override
    public GroupDto create(GroupCreateDto createDto) {
        log.info("Creating group: {}", createDto);

        // prepare entities
        var company = createDto.getCompanyId() != null ? getCompany(createDto.getCompanyId()) : null;
        var user = UserContext.getUser();

        // check if group number is unique
        if (createDto.getNumber() != null && groupRepository.existsByNumberAndTourOperator_UserCompany(
                createDto.getNumber(),
                user.getUserCompany()
        )) {
            throw new ClientException(ResponseCode.BAD_REQUEST, "Group number is not unique: " + createDto.getNumber());
        }

        // save and return group
        Group group = groupRepository.save(new Group(createDto, user, company));
        log.info("Created group with id: {} and number: {}", group.getId(), group.getNumber());

        // send notification
        notificationService.create(NotificationCreateDto.builder()
            .groupId(group.getId())
                .message(String.format("Created group with id: %s and number: %s", group.getId(), group.getNumber()))
                .build());

        return new GroupDto(group);
    }

    @Override
    public GroupDto update(GroupDto dto) {
        log.info("Updating group: {}", dto);

        //update group
        var group = getGroupOfOperator(dto.getId());
        if (dto.getCountry() != null) {
            group.setCountry(dto.getCountry());
        }
        if (dto.getSize() != null) {
            group.setSize(dto.getSize());
        }
        if (dto.getTourLeaderCount() != null) {
            group.setTourLeaderAmount(dto.getTourLeaderCount());
        }
        if (dto.getArrival() != null) {
            group.setArrivalTime(dto.getArrival());
        }
        if (dto.getDeparture() != null) {
            group.setDepartureTime(dto.getDeparture());
        }
        if (dto.getCompanyId() != null) {
            group.setPartnerCompany(
               getCompany(dto.getCompanyId())
            );
        }
        if (dto.getTourOperatorId() != null) {
            group.setTourOperator(
                getUser(dto.getTourOperatorId())
            );
        }
        if (dto.getComment() != null) {
            group.setComment(dto.getComment());
        }

        groupRepository.save(group);

        log.info("Updating applications of group with id: {}", group.getId());
        //TODO: update applications

        return new GroupDto(group);
    }

    @Override
    public void cancel(Long id) {
        log.info("Cancelling group with id: {}", id);

        var group = getGroupOfOperator(id);
        group.setStatus(GroupStatus.CANCELLED);
        groupRepository.save(group);

        log.info("Cancelling applications of group with id: {}", group.getId());
        //TODO: cancel applications
    }

    @Override
    public GroupDto get(Long id) {
        return new GroupDto(getGroupOfOperator(id));
    }

    @Override
    public PageableList<GroupDto> getAllByFiltered(GroupFilter filter) {
        if (filter.getTourOperatorId() == null && user.getRoleNames().contains("TOUR_OPERATOR")) {
            filter.setTourOperatorId(user.getId());
        }

        final Pair<List<Group>, Long> result = groupCustomRepository.findAllFiltered(filter);

        return new PageableList<>(
                result.getSecond(),
                result.getFirst().stream().map(GroupDto::new).toList()
        );
    }

    private Group getGroupOfOperator(Long id) {
        var group = groupRepository.findById(id)
                .orElseThrow(() -> new ClientException(ResponseCode.RESOURCE_NOT_FOUND, "Could not find group with id: " + id));

        if (!Objects.equals(group.getTourOperator().getId().getId())) {
            throw new ClientException(ResponseCode.FORBIDDEN_RESOURCE, "User %s is not allowed to access group %s".getId(), id);
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
