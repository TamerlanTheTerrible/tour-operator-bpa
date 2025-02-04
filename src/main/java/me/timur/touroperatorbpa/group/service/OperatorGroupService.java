package me.timur.touroperatorbpa.group.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.timur.touroperatorbpa.domain.entity.Company;
import me.timur.touroperatorbpa.domain.entity.Group;
import me.timur.touroperatorbpa.domain.entity.User;
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
    private final GroupNumberService groupNumberService;

    @Override
    public GroupDto create(GroupCreateDto createDto, User user) {
        log.info("Creating group: {}", createDto);

        var company = getCompany(createDto.getCompanyId());
        //TODO: check if user is allowed to create group for this company
        // createDto.setNumber(groupNumberService.generate(company.getId()));

        // save and return group
        Group group = groupRepository.save(new Group(createDto, user, company));
        log.info("Created group with id: {} and number: {}", group.getId(), group.getNumber());
        return new GroupDto(group);
    }

    @Override
    public GroupDto update(GroupDto dto, User user) {
        log.info("Updating group: {}", dto);

        //update group
        var group = getGroupOfOperator(dto.getId(), user);
        if (dto.getCountry() != null) {
            group.setCountry(dto.getCountry());
        }
        if (dto.getSize() != null) {
            group.setSize(dto.getSize());
        }
        if (dto.getTourLeaderAmount() != null) {
            group.setTourLeaderAmount(dto.getTourLeaderAmount());
        }
        if (dto.getArrival() != null) {
            group.setArrival(dto.getArrival());
        }
        if (dto.getDeparture() != null) {
            group.setDeparture(dto.getDeparture());
        }
        if (dto.getCompanyId() != null) {
            group.setCompany(
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
    public void cancel(Long id, User user) {
        log.info("Cancelling group with id: {}", id);

        var group = getGroupOfOperator(id, user);
        group.setStatus(GroupStatus.CANCELLED);
        groupRepository.save(group);

        log.info("Cancelling applications of group with id: {}", group.getId());
        //TODO: cancel applications
    }

    @Override
    public GroupDto get(Long id, User user) {
        return new GroupDto(getGroupOfOperator(id, user));
    }

    @Override
    public PageableList<GroupDto> getAllByFiltered(GroupFilter filter, User user) {
        if (filter.getTourOperatorId() == null && user.getRoleNames().contains("TOUR_OPERATOR")) {
            filter.setTourOperatorId(user.getId());
        }

        final Pair<List<Group>, Long> result = groupCustomRepository.findAllFiltered(filter);

        return new PageableList<>(
                result.getSecond(),
                result.getFirst().stream().map(GroupDto::new).toList()
        );
    }

    private Group getGroupOfOperator(Long id, User user) {
        var group = groupRepository.findById(id)
                .orElseThrow(() -> new ClientException(ResponseCode.RESOURCE_NOT_FOUND, "Could not find group with id: " + id));

        if (!Objects.equals(group.getTourOperator().getId(), user.getId())) {
            throw new ClientException(ResponseCode.FORBIDDEN_RESOURCE, "User %s is not allowed to access group %s", user.getId(), id);
        }

        return group;
    }

    private User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ClientException(ResponseCode.RESOURCE_NOT_FOUND, "Could not find user with id: " + id));
    }

    private Company getCompany(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new ClientException(ResponseCode.RESOURCE_NOT_FOUND, "Could not find company with id: " + id));
    }
}
