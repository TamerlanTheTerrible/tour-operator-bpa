//package me.timur.touroperatorbpa.operator.service.impl;
//
//import me.timur.touroperatorbpa.domain.entity.*;
//import me.timur.touroperatorbpa.domain.repository.CompanyRepository;
//import me.timur.touroperatorbpa.domain.repository.GroupRepository;
//import me.timur.touroperatorbpa.domain.repository.UserRepository;
//import me.timur.touroperatorbpa.domain.repository.impl.GroupFilteredFetchRepository;
//import me.timur.touroperatorbpa.exception.ClientException;
//import me.timur.touroperatorbpa.group.model.GroupCreateDto;
//import me.timur.touroperatorbpa.group.model.GroupDto;
//import me.timur.touroperatorbpa.group.model.GroupFilter;
//import me.timur.touroperatorbpa.group.service.impl.OperatorGroupService;
//import me.timur.touroperatorbpa.model.enums.ResponseCode;
//import me.timur.touroperatorbpa.model.enums.Role;
//import me.timur.touroperatorbpa.notification.model.NotificationCreateDto;
//import me.timur.touroperatorbpa.notification.service.NotificationService;
//import me.timur.touroperatorbpa.security.UserContext;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.util.Pair;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class OperatorGroupServiceTest {
//
//    @Mock
//    private GroupRepository groupRepository;
//
//    @Mock
//    private GroupFilteredFetchRepository groupCustomRepository;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private CompanyRepository companyRepository;
//
//    @Mock
//    private NotificationService notificationService;
//
//    @InjectMocks
//    private OperatorGroupService operatorGroupService;
//
//    private User currentUser;
//
//    @BeforeEach
//    void setUp() {
//        currentUser = new User();
//        currentUser.setId(1L);
//    }
//
//    @AfterEach
//    void tearDown() {
//        UserContext.clear();
//    }
//
//    @Test
//    void createGroup_withNullCompanyId_createsGroupSuccessfully() {
//        GroupCreateDto createDto = new GroupCreateDto();
//        createDto.setNumber("123");
//        createDto.setCompanyId(null);
//        createDto.setSize(10);
//        createDto.setArrival(LocalDateTime.now());
//        createDto.setDeparture(LocalDateTime.now().plusDays(1));
//
//        when(groupRepository.existsByNumberAndTourOperator_UserCompany(any(), any())).thenReturn(false);
//        when(groupRepository.save(any(Group.class))).thenReturn(getGroup());
//
//        try (var mockedUserContext = mockStatic(UserContext.class)) {
//            mockedUserContext.when(UserContext::getUser).thenReturn(currentUser);
//
//            GroupDto result = operatorGroupService.create(createDto);
//
//            assertNotNull(result);
//            verify(groupRepository, times(1)).save(any(Group.class));
//            verify(notificationService, times(1)).create(any(NotificationCreateDto.class));
//        }
//    }
//
//    @Test
//    void updateGroup_withNullFields_updatesOnlyProvidedFields() {
//        GroupDto groupDto = new GroupDto();
//        groupDto.setId(1L);
//        groupDto.setNumber("456");
//
//        Group group = new Group();
//        group.setTourOperator(currentUser);
//        group.setNumber("123");
//        when(groupRepository.findById(1L)).thenReturn(Optional.of(group));
//
//        try (var mockedUserContext = mockStatic(UserContext.class)) {
//            mockedUserContext.when(UserContext::getUser).thenReturn(currentUser);
//            GroupDto result = operatorGroupService.update(groupDto);
//
//            assertNotNull(result);
//            assertEquals("456", group.getNumber());
//            verify(groupRepository, times(1)).save(group);
//        }
//    }
//
//    @Test
//    void cancelGroup_withInvalidGroupId_throwsException() {
//        when(groupRepository.findById(1L)).thenReturn(Optional.empty());
//
//        ClientException exception = assertThrows(ClientException.class, () -> operatorGroupService.cancel(1L));
//        assertEquals(ResponseCode.RESOURCE_NOT_FOUND, exception.getResponseCode());
//    }
//
//    @Test
//    void getAllByFiltered_withTourOperatorRole_setsTourOperatorIdInFilter() {
//        GroupFilter filter = new GroupFilter();
//        filter.setTourOperatorId(null);
//
//        UserRole tourOperatorRole = new UserRole();
//        tourOperatorRole.setRole(Role.TOUR_MANAGER);
//        currentUser.setRoles(List.of(tourOperatorRole));
//
//        final Group group = getGroup();
//
//        List<Group> mockGroups = List.of(group);
//        Pair<List<Group>, Long> mockResult = Pair.of(mockGroups, 0L);
//
//        when(groupCustomRepository.findAllFiltered(filter)).thenReturn(mockResult);
//
//        try (var mockedUserContext = mockStatic(UserContext.class)) {
//            mockedUserContext.when(UserContext::getUser).thenReturn(currentUser);
//
//            operatorGroupService.getAllByFiltered(filter);
//
//            assertEquals(currentUser.getId(), filter.getTourOperatorId());
//        }
//    }
//
//    private Group getGroup() {
//        PartnerCompany partnerCompany = new PartnerCompany();
//        partnerCompany.setId(1L);
//
//        final Group group = new Group();
//        group.setPartnerCompany(partnerCompany);
//        group.setTourOperator(currentUser);
//        return group;
//    }
//
//    @Test
//    void getAllByFiltered_withNonTourOperatorRole_setsCompanyIdInFilter() {
//        GroupFilter filter = new GroupFilter();
//        filter.setTourOperatorId(null);
//
//        UserRole tourOperatorRole = new UserRole();
//        tourOperatorRole.setRole(Role.ADMIN);
//        currentUser.setRoles(List.of(tourOperatorRole));
//        currentUser.setUserCompany(new UserCompany());
//        currentUser.getUserCompany().setId(2L);
//
//        try (var mockedUserContext = mockStatic(UserContext.class)) {
//            mockedUserContext.when(UserContext::getUser).thenReturn(currentUser);
//        }
//
//        operatorGroupService.getAllByFiltered(filter);
//
//        assertEquals(2L, filter.getCompanyId());
//    }
//}