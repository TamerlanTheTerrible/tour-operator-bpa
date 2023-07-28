package me.timur.touroperatorbpa.operator.service;

import me.timur.touroperatorbpa.domain.entity.Company;
import me.timur.touroperatorbpa.domain.entity.Group;
import me.timur.touroperatorbpa.domain.entity.User;
import me.timur.touroperatorbpa.domain.repository.CompanyRepository;
import me.timur.touroperatorbpa.domain.repository.GroupRepository;
import me.timur.touroperatorbpa.domain.repository.UserRepository;
import me.timur.touroperatorbpa.model.group.GroupCreateDto;
import me.timur.touroperatorbpa.model.group.GroupDto;
import me.timur.touroperatorbpa.operator.service.impl.OperatorGroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

/**
 * Created by Temurbek Ismoilov on 28/07/23.
 */

//@SpringBootTest
public class OperatorGroupServiceTest {
    @Mock
    private GroupRepository groupRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private OperatorGroupService operatorGroupService;

    @BeforeEach
    public void init() {
        openMocks(this);
    }

    @Test
    public void testCreateGroupWithValidInput() {

        // Prepare test data
        GroupCreateDto createDto = new GroupCreateDto();
        createDto.setNumber("1/20-AB");
        createDto.setArrival(LocalDateTime.of(2023, 7, 19, 10, 0));
        createDto.setTourOperatorId(1L);
        createDto.setCompanyId(1L);

        User user = new User();
        user.setId(1L);
        user.setInitial("AB");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Company company = new Company();
        company.setName("China");
        company.setId(createDto.getCompanyId());
        when(companyRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(company));

        Group group = new Group(createDto, user, company);
        when(groupRepository.save(any(Group.class))).thenReturn(group);

        // Call the create method
        GroupDto result = operatorGroupService.create(createDto);

        // Assert the result
        assertNotNull(result);
        assertEquals(group.getId(), result.getId());
        assertEquals(group.getNumber(), result.getNumber());
        // Add more assertions as needed for other fields
    }
}
