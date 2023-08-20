package me.timur.touroperatorbpa.application.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.timur.touroperatorbpa.domain.entity.Accommodation;
import me.timur.touroperatorbpa.domain.entity.User;
import me.timur.touroperatorbpa.domain.entity.application.ApplicationAccommodation;
import me.timur.touroperatorbpa.domain.entity.Group;
import me.timur.touroperatorbpa.domain.entity.Room;
import me.timur.touroperatorbpa.model.enums.ApplicationStatus;
import me.timur.touroperatorbpa.domain.repository.AccommodationRepository;
import me.timur.touroperatorbpa.domain.repository.ApplicationAccommodationRepository;
import me.timur.touroperatorbpa.domain.repository.GroupRepository;
import me.timur.touroperatorbpa.exception.ClientException;
import me.timur.touroperatorbpa.model.enums.ResponseCode;
import me.timur.touroperatorbpa.model.PageableFilter;
import me.timur.touroperatorbpa.model.PageableList;
import me.timur.touroperatorbpa.application.model.accommodation.AccommodationApplicationCreateDto;
import me.timur.touroperatorbpa.application.model.accommodation.AccommodationApplicationDto;
import me.timur.touroperatorbpa.application.service.ApplicationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Temurbek Ismoilov on 31/07/23.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class AccommodationApplicationServiceImpl implements ApplicationService<AccommodationApplicationCreateDto, AccommodationApplicationDto> {

    private final ApplicationAccommodationRepository applicationAccommodationRepository;
    private final AccommodationRepository accommodationRepository;
    private final GroupRepository groupRepository;

    @Override
    public AccommodationApplicationDto create(AccommodationApplicationCreateDto createDto, User user) {
        log.info("Attempting to create accommodation application: {}", createDto);

        var group = getGroup(createDto.getGroupId());
        if (!Objects.equals(group.getTourOperator().getId(), user.getId())) {
            throw new ClientException(ResponseCode.FORBIDDEN_RESOURCE, "User {} is not allowed to create application for this group {}", user.getId(), group.getId());
        }

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
    public List<AccommodationApplicationDto> update(AccommodationApplicationDto dto, User user) {
        log.info("Attempting to update accommodation application: {}", dto);

        var group = getGroup(dto.getGroupId());

        // get latest applications and filter only active ones. Throw exception if no active applications found
        var allApplications = applicationAccommodationRepository.findAllByGroupIdOrderByVersionDesc(dto.getGroupId());
        var latestApplications = new ArrayList<>(allApplications.stream().filter(application -> application.getStatus() == ApplicationStatus.ACTIVE).toList());
        if (latestApplications.isEmpty()) {
            throw new ClientException(ResponseCode.BAD_REQUEST, "Cannot find active applications for group: " + dto.getGroupId());
        }

        // update applications and create new applications based on them
        var newApplications = updateApplication(dto, latestApplications, group);

        // if there are non-updated applications then change their status to deprecated and create new applications based on them
        latestApplications.stream().filter(application -> application.getStatus() == ApplicationStatus.ACTIVE)
                .forEach(application -> {
                    application.setStatus(ApplicationStatus.DEPRECATED);
                    newApplications.add(new ApplicationAccommodation(application));
                });
        
        // save latest applications and new applications
        latestApplications.addAll(newApplications);
        applicationAccommodationRepository.saveAll(latestApplications);

        // map applications for response
        var applicationMap = allApplications.stream().collect(Collectors.groupingBy(ApplicationAccommodation::getVersion));
        applicationMap.put(newApplications.get(0).getVersion(), newApplications);
        var applicationDtos = new ArrayList<AccommodationApplicationDto>();
        var currentVersion = newApplications.get(0).getVersion();
        for (int i = currentVersion; i > 0; i--) {
            var applications = applicationMap.get(i);
            if (applications != null) {
                applicationDtos.add(new AccommodationApplicationDto(group, applications));
            }
        }

        return applicationDtos;
    }

    private ArrayList<ApplicationAccommodation> updateApplication(AccommodationApplicationDto dto, ArrayList<ApplicationAccommodation> latestApplications, Group group) {
        //start creating new applications based on dto
        var newApplications = new ArrayList<ApplicationAccommodation>();
        // create map of latest applications to easily find them by id
        var latestApplicationMap = latestApplications.stream().collect(Collectors.toMap(ApplicationAccommodation::getId, Function.identity()));
        for (var item: dto.getItems()) {
            ApplicationAccommodation newApplication = null;
            // if application id is not null then update application, else create new application
            if (item.getId() != null) {
                log.info("Updating application: {}", item.getId());
                // get applications from map. Throw exception if application is not found
                var application = latestApplicationMap.get(item.getId());
                if (application == null) {
                    throw new ClientException(ResponseCode.BAD_REQUEST, "Cannot find active application with id: " + item.getId());
                }
                // change status to deprecated and create new application based on
                application.setStatus(ApplicationStatus.DEPRECATED);
                newApplication = new ApplicationAccommodation(application);
                if (item.getCheckIn() != null) {
                    newApplication.setCheckIn(item.getCheckIn());
                }
                if (item.getCheckOut() != null) {
                    newApplication.setCheckOut(item.getCheckOut());
                }
                if (item.getAccommodationId() != null) {
                    final Accommodation newAccommodation = getAccommodation(item.getAccommodationId());
                    newApplication.setAccommodation(newAccommodation);
                }
                if (item.getRooms() != null && !item.getRooms().isEmpty()) {
                    var rooms = item.getRooms().stream().map(roomDto -> new Room(application, roomDto)).toList();
                    newApplication.addRooms(rooms);
                }
                if (item.getStatus() != null) {
                    newApplication.setStatus(item.getStatus());
                }
                if (item.getComment() != null) {
                    newApplication.setComment(item.getComment());
                }
            } else {
                log.info("Attempting to create accommodation application: {}", item);
                var version = latestApplications.get(0).getVersion();
                newApplication = new ApplicationAccommodation(group, getAccommodation(item.getAccommodationId()), item, version + 1);
                ApplicationAccommodation finalNewApplication = newApplication;
                newApplication.addRooms(
                        item.getRooms().stream().map(roomDto -> new Room(finalNewApplication, roomDto)).toList()
                );
            }
            newApplications.add(newApplication);
        }
        return newApplications;
    }

    @Override
    public void changeStatus(Long id) {
        log.info("Attempting to cancel accommodation application with id: {}", id);
        var application = getApplicationEntity(id);
        application.setStatus(ApplicationStatus.CANCELLED);
        applicationAccommodationRepository.save(application);

        // TODO send notifications
    }

    @Override
    public void cancelByGroupId(Long groupId) {
        log.info("Attempting to cancel accommodation applications with group id: {}", groupId);
        var applications = applicationAccommodationRepository.findAllByGroupIdAndStatus(groupId, ApplicationStatus.ACTIVE);
        applications.forEach(application -> application.setStatus(ApplicationStatus.CANCELLED));
        applicationAccommodationRepository.saveAll(applications);

        // TODO send notifications
    }

    @Override
    public AccommodationApplicationDto get(Long id) {
        return null;
    }

    @Override
    public List<AccommodationApplicationDto> getByGroupId(Long groupId) {
        var applications = applicationAccommodationRepository.findAllByGroupIdOrderByVersionDesc(groupId);
        return List.of(new AccommodationApplicationDto(getGroup(groupId), applications));
    }

    @Override
    public PageableList<AccommodationApplicationDto.AccommodationItem> getAllFiltered(PageableFilter pageableFilter) {
        return null;
    }

    private ApplicationAccommodation getApplicationEntity(Long id) {
        return applicationAccommodationRepository.findById(id)
                .orElseThrow(() -> new ClientException(ResponseCode.RESOURCE_NOT_FOUND, "Could not find application with id: " + id));
    }


    private Accommodation getAccommodation(Long accommodationId) {
        return accommodationRepository.findById(accommodationId)
                .orElseThrow(() -> new ClientException(ResponseCode.RESOURCE_NOT_FOUND, "Could not find accommodation with id: " + accommodationId));
    }

    private Group getGroup(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new ClientException(ResponseCode.RESOURCE_NOT_FOUND, "Could not find group with id: " + groupId));
    }

}
