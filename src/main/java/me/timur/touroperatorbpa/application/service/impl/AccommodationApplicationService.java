package me.timur.touroperatorbpa.application.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.timur.touroperatorbpa.domain.entity.Accommodation;
import me.timur.touroperatorbpa.domain.entity.ApplicationAccommodation;
import me.timur.touroperatorbpa.domain.entity.Group;
import me.timur.touroperatorbpa.domain.entity.Room;
import me.timur.touroperatorbpa.model.enums.ApplicationStatus;
import me.timur.touroperatorbpa.domain.repository.AccommodationRepository;
import me.timur.touroperatorbpa.domain.repository.ApplicationAccommodationRepository;
import me.timur.touroperatorbpa.domain.repository.GroupRepository;
import me.timur.touroperatorbpa.exception.OperatorBpaException;
import me.timur.touroperatorbpa.exception.ResponseCode;
import me.timur.touroperatorbpa.model.PageableFilter;
import me.timur.touroperatorbpa.model.PageableList;
import me.timur.touroperatorbpa.application.model.accommodation.AccommodationApplicationCreateDto;
import me.timur.touroperatorbpa.application.model.accommodation.AccommodationApplicationDto;
import me.timur.touroperatorbpa.application.service.ApplicationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by Temurbek Ismoilov on 31/07/23.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class AccommodationApplicationService implements ApplicationService<AccommodationApplicationCreateDto, AccommodationApplicationDto> {

    private final ApplicationAccommodationRepository applicationAccommodationRepository;
    private final AccommodationRepository accommodationRepository;
    private final GroupRepository groupRepository;

    @Override
    public AccommodationApplicationDto create(AccommodationApplicationCreateDto createDto) {
        log.info("Attempting to create accommodation application: {}", createDto);

        var group = getGroup(createDto.getGroupId());
        var applications = new ArrayList<ApplicationAccommodation>();
        for (var item: createDto.getItems()) {
            var application = new ApplicationAccommodation(group, getAccommodation(item.getAccommodationId()), item);
            application.addRooms(
                    item.getRooms().stream().map(roomDto -> new Room(application, roomDto)).toList()
            );
            applications.add(application);
        }

        applicationAccommodationRepository.saveAll(applications);

        // TODO send notifications

        return new AccommodationApplicationDto(group, applications);
    }

    @Override
    public AccommodationApplicationDto update(AccommodationApplicationDto dto) {
        log.info("Attempting to update accommodation application: {}", dto);

        var group = getGroup(dto.getGroupId());
        var applications = new ArrayList<ApplicationAccommodation>();
        for (var item: dto.getItems()) {
            // if application id is not null then update application, else create new application
            if (item.getId() != null) {
                log.info("Updating application: {}", item);

                var application = getApplicationEntity(item.getId());
                if (item.getAccommodationId() != null) {
                    application.setAccommodation(getAccommodation(item.getAccommodationId()));
                }
                if (item.getCheckIn() != null) {
                    application.setCheckIn(item.getCheckIn());
                }
                if (item.getCheckOut() != null) {
                    application.setCheckOut(item.getCheckOut());
                }
                if (item.getRooms() != null && item.getRooms().size() > 0) {
                    var rooms = item.getRooms().stream().map(roomDto -> new Room(application, roomDto)).toList();
                    application.addRooms(rooms);
                }
                if (item.getStatus() != null) {
                    application.setStatus(item.getStatus());
                }
                if (item.getComment() != null) {
                    application.setComment(item.getComment());
                }

                applications.add(application);
            } else {
                log.info("Attempting to create accommodation application: {}", item);
                var application = new ApplicationAccommodation(group, getAccommodation(item.getAccommodationId()), item);
                application.addRooms(
                        item.getRooms().stream().map(roomDto -> new Room(application, roomDto)).toList()
                );

                applications.add(application);
            }
        }

        applicationAccommodationRepository.saveAll(applications);

        // TODO send notifications

        return new AccommodationApplicationDto(group, applications);
    }

    @Override
    public void cancel(Long id) {
        log.info("Attempting to cancel accommodation application with id: {}", id);
        var application = getApplicationEntity(id);
        application.setStatus(ApplicationStatus.CANCELLED);
        applicationAccommodationRepository.save(application);

        // TODO send notifications
    }

    @Override
    public void cancelByGroupId(Long groupId) {
        log.info("Attempting to cancel accommodation applications with group id: {}", groupId);
        var applications = applicationAccommodationRepository.findAllByGroupId(groupId);
        applications.forEach(application -> application.setStatus(ApplicationStatus.CANCELLED));
        applicationAccommodationRepository.saveAll(applications);

        // TODO send notifications
    }

    @Override
    public AccommodationApplicationDto get(Long id) {
        return null;
    }

    @Override
    public AccommodationApplicationDto getByGroupId(Long groupId) {
        var applications = applicationAccommodationRepository.findAllByGroupId(groupId);
        return new AccommodationApplicationDto(getGroup(groupId), applications);
    }

    @Override
    public PageableList<AccommodationApplicationDto.AccommodationItem> getAllFiltered(PageableFilter pageableFilter) {
        return null;
    }

    private ApplicationAccommodation getApplicationEntity(Long id) {
        return applicationAccommodationRepository.findById(id)
                .orElseThrow(() -> new OperatorBpaException(ResponseCode.RESOURCE_NOT_FOUND, "Could not find application with id: " + id));
    }


    private Accommodation getAccommodation(Long accommodationId) {
        return accommodationRepository.findById(accommodationId)
                .orElseThrow(() -> new OperatorBpaException(ResponseCode.RESOURCE_NOT_FOUND, "Could not find accommodation with id: " + accommodationId));
    }

    private Group getGroup(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new OperatorBpaException(ResponseCode.RESOURCE_NOT_FOUND, "Could not find group with id: " + groupId));
    }

}
