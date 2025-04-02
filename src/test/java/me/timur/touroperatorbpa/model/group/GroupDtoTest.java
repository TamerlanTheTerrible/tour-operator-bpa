package me.timur.touroperatorbpa.model.group;

import me.timur.touroperatorbpa.domain.entity.PartnerCompany;
import me.timur.touroperatorbpa.domain.entity.Group;
import me.timur.touroperatorbpa.domain.entity.User;
import me.timur.touroperatorbpa.group.model.GroupDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Temurbek Ismoilov on 28/07/23.
 */

public class GroupDtoTest {
    @Test
    public void testGroupDtoConstructor() {
        // Create a mock Group object with test data
        Group group = new Group();
        group.setNumber("GRP123");
        group.setCountry("Country1");

        // Create a mock Company object for the Group
        PartnerCompany partnerCompany = new PartnerCompany();
        partnerCompany.setId(1L);
        partnerCompany.setName("Company1");
        group.setPartnerCompany(partnerCompany);

        group.setSize(20);
        group.setTourLeaderAmount(2);
        group.setArrivalTime(LocalDateTime.of(2023, 7, 19, 10, 0));
        group.setDepartureTime(LocalDateTime.of(2023, 7, 25, 12, 0));
        group.setComment("Test group");

        // Create a mock User object for the tourOperator
        User tourOperator = new User();
        tourOperator.setId(2L);
        group.setTourOperator(tourOperator);

        // Create the GroupDto object using the constructor
        GroupDto groupDto = new GroupDto(group);

        // Assert that the GroupDto object is created correctly with the given values
        assertEquals("GRP123", groupDto.getNumber());
        assertEquals("Country1", groupDto.getCountry());
        assertEquals(1L, groupDto.getCompanyId());
        assertEquals("Company1", groupDto.getCompanyName());
        assertEquals(20, groupDto.getSize());
        assertEquals(2, groupDto.getTourLeaderCount());
        assertEquals(LocalDateTime.of(2023, 7, 19, 10, 0), groupDto.getArrival());
        assertEquals(LocalDateTime.of(2023, 7, 25, 12, 0), groupDto.getDeparture());
        assertEquals("Test group", groupDto.getComment());
        assertEquals(2L, groupDto.getTourOperatorId());
    }
}
