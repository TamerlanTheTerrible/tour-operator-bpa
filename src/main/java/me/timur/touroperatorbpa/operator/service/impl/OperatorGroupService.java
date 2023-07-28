package me.timur.touroperatorbpa.operator.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.timur.touroperatorbpa.domain.entity.Group;
import me.timur.touroperatorbpa.domain.entity.User;
import me.timur.touroperatorbpa.domain.repository.CompanyRepository;
import me.timur.touroperatorbpa.domain.repository.GroupRepository;
import me.timur.touroperatorbpa.domain.repository.UserRepository;
import me.timur.touroperatorbpa.exception.OperatorBpaException;
import me.timur.touroperatorbpa.exception.ResponseCode;
import me.timur.touroperatorbpa.model.group.GroupCreateDto;
import me.timur.touroperatorbpa.model.group.GroupDto;
import me.timur.touroperatorbpa.operator.service.GroupService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Temurbek Ismoilov on 28/07/23.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class OperatorGroupService implements GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    @Override
    public GroupDto create(GroupCreateDto createDto) {
        log.info("Creating group: {}", createDto);

        var user = getUser(createDto.getTourOperatorId());
        var company = companyRepository.findById(createDto.getCompanyId())
                .orElseThrow(() -> new OperatorBpaException(ResponseCode.RESOURCE_NOT_FOUND, "Could not find company with id: " + createDto.getCompanyId()));

        // validate and set group number
        if (createDto.getNumber() != null) {
            if (!isValidGroupNumber(createDto.getNumber())) {
                throw new OperatorBpaException(ResponseCode.BAD_REQUEST, "Invalid group number: " + createDto.getNumber());
            }
        } else {
            var groupNumber = generateGroupNumber(createDto.getArrival(), user.getInitial());
            createDto.setNumber(groupNumber);
        }

        // save and return group
        Group group = groupRepository.save(new Group(createDto, user, company));
        log.info("Created group with id: {}", group.getId());

        return new GroupDto(group);
    }

    @Override
    public GroupDto update(GroupDto groupDto) {
        return null;
    }

    @Override
    public void cancel(Long id) {

    }

    @Override
    public GroupDto get(Long id) {
        return null;
    }

    @Override
    public List<GroupDto> getAllByOperatorId(Long id) {
        return null;
    }

    private User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new OperatorBpaException(ResponseCode.RESOURCE_NOT_FOUND, "Could not find user with id: " + id));
    }

    public static boolean isValidGroupNumber(String input) {
        // Define the regular expression pattern
        String regex = "^[1-9][0-9]?/[0-9]{1,2}-[A-Z]{1,2}$";
        // Create a Pattern object
        Pattern pattern = Pattern.compile(regex);
        // Check if the input matches the pattern
        return pattern.matcher(input).matches();
    }

    private String generateGroupNumber(LocalDateTime arrival, String initials) {
        // validate arrival and initials
        if (arrival == null || initials == null) {
            throw new OperatorBpaException(ResponseCode.BAD_REQUEST, "Arrival date or initials is null");
        }
        // count groups by arrival month
        var arrivalMonth = arrival.getMonth();
        var count = groupRepository.countByAndArrivalBetween(
                arrival.withDayOfMonth(1),
                arrival.withDayOfMonth(arrivalMonth.maxLength())
        );
        // return group number
        return arrivalMonth.getValue() + "/" + ++count + "-" + initials;
    }
}
