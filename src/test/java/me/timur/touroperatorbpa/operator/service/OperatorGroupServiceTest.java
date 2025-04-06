//package me.timur.touroperatorbpa.operator.service;
//
//import me.timur.touroperatorbpa.domain.entity.PartnerCompany;
//import me.timur.touroperatorbpa.domain.entity.Group;
//import me.timur.touroperatorbpa.domain.entity.User;
//import me.timur.touroperatorbpa.domain.repository.CompanyRepository;
//import me.timur.touroperatorbpa.domain.repository.GroupRepository;
//import me.timur.touroperatorbpa.domain.repository.UserRepository;
//import me.timur.touroperatorbpa.group.model.GroupCreateDto;
//import me.timur.touroperatorbpa.group.model.GroupDto;
//import me.timur.touroperatorbpa.group.service.impl.OperatorGroupService;
//import me.timur.touroperatorbpa.group.service.GroupNumberService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
///**
// * Created by Temurbek Ismoilov on 28/07/23.
// */
//
//@ExtendWith(MockitoExtension.class)
//public class OperatorGroupServiceTest {
//    @Mock
//    private GroupRepository groupRepository;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private CompanyRepository companyRepository;
//
//    @Mock
//    private GroupNumberService groupNumberService;
//
//    @InjectMocks
//    private OperatorGroupService operatorGroupService;
//
//    @Test
//    public void testCreateGroupWithValidInput() {
//
//        // Prepare test data
//        GroupCreateDto createDto = new GroupCreateDto();
//        createDto.setNumber("1/20-AB");
//        createDto.setArrival(LocalDateTime.of(2023, 7, 19, 10, 0));
//        createDto.setTourOperatorId(1L);
//        createDto.setCompanyId(1L);
//
//        User user = new User();
//        user.setId(1L);
//        user.setInitials("AB");
//        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
//
//        PartnerCompany partnerCompany = new PartnerCompany();
//        partnerCompany.setName("China");
//        partnerCompany.setId(createDto.getCompanyId());
//        when(companyRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(partnerCompany));
//
//        Group group = new Group(createDto, user, partnerCompany);
//        when(groupRepository.save(any(Group.class))).thenReturn(group);
//
//        // Call the create method
//        GroupDto result = operatorGroupService.create(createDto, new User());
//
//        // Assert the result
//        assertNotNull(result);
//        assertEquals(group.getId(), result.getId());
//        assertEquals(group.getNumber(), result.getNumber());
//    }
//}
