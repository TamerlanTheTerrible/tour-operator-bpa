package me.timur.touroperatorbpa.group.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.timur.touroperatorbpa.domain.repository.GroupRepository;
import me.timur.touroperatorbpa.exception.ClientException;
import me.timur.touroperatorbpa.model.enums.ResponseCode;
import me.timur.touroperatorbpa.group.model.GroupCreateDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

/**
 * Created by Temurbek Ismoilov on 29/07/23.
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class GroupNumberService {

    private final GroupRepository groupRepository;

    public String getValidNumber(GroupCreateDto createDto, String initials) {
        String number = createDto.getNumber();

        if (number != null) {
            if (!isValidGroupNumber(number)) {
                throw new ClientException(ResponseCode.BAD_REQUEST, "Invalid group number: " + number);
            }
            if(isNotUniqueGroupNumber(number, createDto.getArrival())) {
                throw new ClientException(ResponseCode.BAD_REQUEST, "Group number is not unique: " + number);
            }
        } else {
            number = generateGroupNumber(createDto.getArrival(), initials);
        }

        return number;
    }

    public static boolean isValidGroupNumber(String input) {
        // Define the regular expression pattern
        String regex = "^[1-9][0-9]?/[0-9]{1,2}-[A-Z]{1,2}$";
        // Create a Pattern object
        Pattern pattern = Pattern.compile(regex);
        // Check if the input matches the pattern
        return pattern.matcher(input).matches();
    }

    private boolean isNotUniqueGroupNumber(String groupNumber, LocalDateTime arrival) {
        var monthAndNumber = groupNumber.split("-")[0];
        var arrivalMonth = arrival.getMonth();
        return groupRepository.existsByNumberStartsWithAndArrivalBetween(
                monthAndNumber,
                arrival.withDayOfMonth(1),
                arrival.withDayOfMonth(arrivalMonth.maxLength())
        );
    }

    private String generateGroupNumber(LocalDateTime arrival, String initials) {
        // validate arrival and initials
        if (arrival == null || initials == null) {
            throw new ClientException(ResponseCode.BAD_REQUEST, "Arrival date or initials is null");
        }
        // count groups by arrival month
        var arrivalMonth = arrival.getMonth();
        var count = groupRepository.countByArrivalBetween(
                arrival.withDayOfMonth(1),
                arrival.withDayOfMonth(arrivalMonth.maxLength())
        );
        // return group number
        return arrivalMonth.getValue() + "/" + ++count + "-" + initials;
    }

}
