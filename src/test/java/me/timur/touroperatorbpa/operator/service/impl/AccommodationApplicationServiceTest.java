package me.timur.touroperatorbpa.operator.service.impl;

import me.timur.touroperatorbpa.domain.entity.Accommodation;
import me.timur.touroperatorbpa.domain.entity.ApplicationAccommodation;
import me.timur.touroperatorbpa.domain.entity.Group;
import me.timur.touroperatorbpa.domain.enums.ApplicationStatus;
import me.timur.touroperatorbpa.domain.enums.RoomType;
import me.timur.touroperatorbpa.domain.repository.AccommodationRepository;
import me.timur.touroperatorbpa.domain.repository.ApplicationAccommodationRepository;
import me.timur.touroperatorbpa.domain.repository.GroupRepository;
import me.timur.touroperatorbpa.model.application.RoomDto;
import me.timur.touroperatorbpa.model.application.accommodation.AccommodationApplicationCreateDto;
import me.timur.touroperatorbpa.model.application.accommodation.AccommodationApplicationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccommodationApplicationServiceTest {

    @InjectMocks
    private AccommodationApplicationService accommodationApplicationService;

    @Mock
    private ApplicationAccommodationRepository applicationAccommodationRepository;

    @Mock
    private AccommodationRepository accommodationRepository;

    @Mock
    private GroupRepository groupRepository;

    @Captor
    private ArgumentCaptor<List<ApplicationAccommodation>> applicationsCaptor;

    @BeforeEach
    public void setUp() {
//        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate() {
        // GIVEN
        // Create some test data for AccommodationApplicationCreateDto
        List<AccommodationApplicationCreateDto.AccommodationItem> items = new ArrayList<>();
        AccommodationApplicationCreateDto.AccommodationItem item0 = new AccommodationApplicationCreateDto.AccommodationItem();
        item0.setAccommodationId(2L);
        item0.setCheckIn(LocalDateTime.of(2023, 1, 1, 12, 0));
        item0.setCheckOut(LocalDateTime.of(2023, 1, 5, 12, 0));
        List<RoomDto> roomDtos = List.of(new RoomDto(RoomType.SINGLE, 2), new RoomDto(RoomType.TWIN, 1));
        item0.setRooms(roomDtos);
        items.add(item0);

        AccommodationApplicationCreateDto createDto = new AccommodationApplicationCreateDto();
        createDto.setGroupId(1L);
        createDto.setItems(items);


        // Mock the behavior of the repositories
        Accommodation accommodation = new Accommodation();
        accommodation.setId(item0.getAccommodationId());
        when(accommodationRepository.findById(item0.getAccommodationId())).thenReturn(java.util.Optional.of(accommodation));

        Group group = new Group();
        group.setId(createDto.getGroupId());
        when(groupRepository.findById(createDto.getGroupId())).thenReturn(java.util.Optional.of(group));


        // WHEN
        AccommodationApplicationDto result = accommodationApplicationService.create(createDto);


        // THEN
        AccommodationApplicationDto.AccommodationItem resultDtoItem0 = result.getItems().get(0);
        verify(groupRepository, times(1)).findById(1L);
        verify(accommodationRepository, times(1)).findById(2L);
        verify(applicationAccommodationRepository, times(1)).saveAll(applicationsCaptor.capture());
//        // Check if the saved entities match the input data
//        List<ApplicationAccommodation> savedApplications = applicationsCaptor.getValue();
//        ApplicationAccommodation savedApplication = savedApplications.get(0);
        assertEquals(1, result.getItems().size());
        assertEquals(1L, result.getGroupId().longValue());
        assertEquals(createDto.getItems().size(), result.getItems().size());
        assertEquals(createDto.getGroupId(), result.getGroupId());
        assertEquals(item0.getAccommodationId(), resultDtoItem0.getAccommodationId());
        assertEquals(item0.getCheckIn(), resultDtoItem0.getCheckIn());
        assertEquals(item0.getCheckOut(), resultDtoItem0.getCheckOut());
        assertEquals(ApplicationStatus.ACTIVE, result.getStatus());
        assertEquals(item0.getRooms().size(), resultDtoItem0.getRooms().size());
    }

    @Test
    public void testUpdate() {
        // Create some test data
        Long groupId = 1L;
        Long accommodationId = 101L;

        AccommodationApplicationDto.AccommodationItem item = new AccommodationApplicationDto.AccommodationItem();
        item.setId(1L);
        item.setAccommodationId(accommodationId);
        item.setCheckIn(LocalDateTime.of(2023, Month.JANUARY, 1, 12, 0));
        item.setCheckOut(LocalDateTime.of(2023, Month.JANUARY, 5, 12, 0));
        item.setComment("Test comment");

        RoomDto roomDto = new RoomDto(RoomType.SINGLE, 2);
        List<RoomDto> rooms = new ArrayList<>();
        rooms.add(roomDto);
        item.setRooms(rooms);

        List<AccommodationApplicationDto.AccommodationItem> items = new ArrayList<>();
        items.add(item);

        AccommodationApplicationDto dto = new AccommodationApplicationDto();
        dto.setGroupId(groupId);
        dto.setGroupNumber("Group-123");
        dto.setItems(items);

        Group group = new Group();
        group.setId(groupId);
        group.setNumber(dto.getGroupNumber());

        Accommodation accommodation = new Accommodation();
        accommodation.setId(accommodationId);

        ApplicationAccommodation application = new ApplicationAccommodation(group, accommodation, item);
        application.setId(item.getId());

        // Mock the repository methods
        when(groupRepository.findById(groupId)).thenReturn(Optional.of(group));
        when(accommodationRepository.findById(accommodationId)).thenReturn(Optional.of(accommodation));
        when(applicationAccommodationRepository.findById(item.getId())).thenReturn(Optional.of(application));
        when(applicationAccommodationRepository.saveAll(any())).thenReturn(null);

        // Call the update() method
        AccommodationApplicationDto resultDto = accommodationApplicationService.update(dto);

        // Verify the interactions
        verify(groupRepository, times(1)).findById(groupId);
        verify(accommodationRepository, times(1)).findById(accommodationId);
        verify(applicationAccommodationRepository, times(1)).saveAll(any());

        // Assert the result
        assertEquals(dto.getGroupId(), resultDto.getGroupId());
        assertEquals(dto.getGroupNumber(), resultDto.getGroupNumber());

        // Assert the item
        assertEquals(dto.getItems().size(), resultDto.getItems().size());
        AccommodationApplicationDto.AccommodationItem resultItem = resultDto.getItems().get(0);
        assertEquals(item.getId(), resultItem.getId());
        assertEquals(item.getAccommodationId(), resultItem.getAccommodationId());
        assertEquals(item.getCheckIn(), resultItem.getCheckIn());
        assertEquals(item.getCheckOut(), resultItem.getCheckOut());
        assertEquals(item.getComment(), resultItem.getComment());

        // Assert the rooms
        assertEquals(item.getRooms().size(), resultItem.getRooms().size());
        RoomDto resultRoomDto = resultItem.getRooms().get(0);
        assertEquals(roomDto.getRoomType(), resultRoomDto.getRoomType());
        assertEquals(roomDto.getRequested(), resultRoomDto.getRequested());
    }

}