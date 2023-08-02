package me.timur.touroperatorbpa.entity;

import me.timur.touroperatorbpa.domain.entity.Company;
import me.timur.touroperatorbpa.domain.entity.Group;
import me.timur.touroperatorbpa.domain.entity.User;
import me.timur.touroperatorbpa.group.model.GroupCreateDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Temurbek Ismoilov on 28/07/23.
 */

public class GroupTest {
    @Test
    public void testGroupConstructor() {
        // Create a mock User object for tourOperator
        User tourOperator = new User(); // Replace with actual User instantiation
        Company company = new Company(); // Replace with actual Company instantiation
        company.setName("Company1");

        // Create a GroupCreateDto with test data
        GroupCreateDto dto = new GroupCreateDto();
        dto.setNumber("GRP123");
        dto.setCountry("Country1");
        dto.setSize(20);
        dto.setTourLeaderAmount(2);
        dto.setArrival(LocalDateTime.of(2023, 7, 19, 10, 0));
        dto.setDeparture(LocalDateTime.of(2023, 7, 25, 12, 0));
        dto.setComment("Test group");

        // Create the Group object using the constructor
        Group group = new Group(dto, tourOperator, company);

        // Assert that the Group object is created correctly with the given values
        assertEquals(tourOperator, group.getTourOperator());
        assertEquals("GRP123", group.getNumber());
        assertEquals("Country1", group.getCountry());
        assertEquals(company.getName(), group.getCompany().getName());
        assertEquals(20, group.getSize());
        assertEquals(2, group.getTourLeaderAmount());
        assertEquals(LocalDateTime.of(2023, 7, 19, 10, 0), group.getArrival());
        assertEquals(LocalDateTime.of(2023, 7, 25, 12, 0), group.getDeparture());
        assertEquals("Test group", group.getComment());
    }
}
