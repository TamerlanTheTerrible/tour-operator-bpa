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
import me.timur.touroperatorbpa.domain.repository.impl.GroupFilteredFetchReposiory;
import me.timur.touroperatorbpa.exception.OperatorBpaException;
import me.timur.touroperatorbpa.exception.ResponseCode;
import me.timur.touroperatorbpa.model.PageableList;
import me.timur.touroperatorbpa.group.model.GroupCreateDto;
import me.timur.touroperatorbpa.group.model.GroupDto;
import me.timur.touroperatorbpa.group.model.GroupFilter;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 28/07/23.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class OperatorGroupService implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupFilteredFetchReposiory groupCustomRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final GroupNumberService groupNumberService;

    @Override
    public GroupDto create(GroupCreateDto createDto) {
        log.info("Creating group: {}", createDto);

        var user = getUser(createDto.getTourOperatorId());
        var company = getCompany(createDto.getCompanyId());
        createDto.setNumber(groupNumberService.getValidNumber(createDto, user.getInitial()));

        // save and return group
        Group group = groupRepository.save(new Group(createDto, user, company));
        log.info("Created group with id: {} and number: {}", group.getId(), group.getNumber());
        return new GroupDto(group);
    }

    @Override
    public GroupDto update(GroupDto dto) {
        log.info("Updating group: {}", dto);

        //update group
        var group = getEntity(dto.getId());
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
    public void cancel(Long id) {
        log.info("Cancelling group with id: {}", id);

        var group = getEntity(id);
        group.setStatus(GroupStatus.CANCELLED);
        groupRepository.save(group);

        log.info("Cancelling applications of group with id: {}", group.getId());
        //TODO: cancel applications
    }

    @Override
    public GroupDto get(Long id) {
        return new GroupDto(getEntity(id));
    }

    @Override
    public List<GroupDto> getAllByOperatorId(Long id) {
        return groupRepository.findAllByTourOperatorId(id).stream()
                .map(GroupDto::new)
                .toList();
    }

    @Override
    public PageableList<GroupDto> getAllByFiltered(GroupFilter filter) {
        final Pair<List<Group>, Long> result = groupCustomRepository.findAllFiltered(filter);

        return new PageableList<>(
                result.getSecond(),
                result.getFirst().stream().map(GroupDto::new).toList()
        );
    }

    private Group getEntity(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new OperatorBpaException(ResponseCode.RESOURCE_NOT_FOUND, "Could not find group with id: " + id));
    }

    private User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new OperatorBpaException(ResponseCode.RESOURCE_NOT_FOUND, "Could not find user with id: " + id));
    }

    private Company getCompany(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new OperatorBpaException(ResponseCode.RESOURCE_NOT_FOUND, "Could not find company with id: " + id));
    }
}
