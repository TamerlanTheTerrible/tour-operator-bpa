package me.timur.touroperatorbpa.entity;

import me.timur.touroperatorbpa.domain.entity.PartnerCompany;
import me.timur.touroperatorbpa.domain.entity.Group;
import me.timur.touroperatorbpa.domain.entity.User;
import me.timur.touroperatorbpa.group.model.GroupCreateDto;
import me.timur.touroperatorbpa.model.enums.Country;
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
        PartnerCompany partnerCompany = new PartnerCompany(); // Replace with actual Company instantiation
        partnerCompany.setName("Company1");

        // Create a GroupCreateDto with test data
        GroupCreateDto dto = new GroupCreateDto();
        dto.setNumber("GRP123");
        dto.setCountry(Country.ALGERIA);
        dto.setSize(20);
        dto.setTourLeaderCount(2);
        dto.setArrival(LocalDateTime.of(2023, 7, 19, 10, 0));
        dto.setDeparture(LocalDateTime.of(2023, 7, 25, 12, 0));
        dto.setComment("Test group");

        // Create the Group object using the constructor
        Group group = new Group(dto, tourOperator, partnerCompany);

        // Assert that the Group object is created correctly with the given values
        assertEquals(tourOperator, group.getTourOperator());
        assertEquals("GRP123", group.getNumber());
        assertEquals(Country.ALGERIA, group.getCountry());
        assertEquals(partnerCompany.getName(), group.getPartnerCompany().getName());
        assertEquals(20, group.getSize());
        assertEquals(2, group.getTourLeaderCount());
        assertEquals(LocalDateTime.of(2023, 7, 19, 10, 0), group.getArrivalTime());
        assertEquals(LocalDateTime.of(2023, 7, 25, 12, 0), group.getDepartureTime());
        assertEquals("Test group", group.getComment());
    }
}
