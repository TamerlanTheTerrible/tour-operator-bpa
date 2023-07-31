package me.timur.touroperatorbpa.operator.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.timur.touroperatorbpa.domain.entity.Accommodation;
import me.timur.touroperatorbpa.domain.entity.ApplicationAccommodation;
import me.timur.touroperatorbpa.domain.entity.Group;
import me.timur.touroperatorbpa.domain.entity.Room;
import me.timur.touroperatorbpa.domain.repository.AccommodationRepository;
import me.timur.touroperatorbpa.domain.repository.ApplicationAccommodationRepository;
import me.timur.touroperatorbpa.domain.repository.GroupRepository;
import me.timur.touroperatorbpa.exception.OperatorBpaException;
import me.timur.touroperatorbpa.exception.ResponseCode;
import me.timur.touroperatorbpa.model.BaseFilter;
import me.timur.touroperatorbpa.model.application.Application;
import me.timur.touroperatorbpa.model.application.ApplicationCreate;
import me.timur.touroperatorbpa.model.application.impl.AccommodationApplicationCreateDto;
import me.timur.touroperatorbpa.model.application.impl.AccommodationApplicationDto;
import me.timur.touroperatorbpa.operator.service.ApplicationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    private Accommodation getAccommodation(Long accommodationId) {
        return accommodationRepository.findById(accommodationId)
                .orElseThrow(() -> new OperatorBpaException(ResponseCode.RESOURCE_NOT_FOUND, "Could not find accommodation with id: " + accommodationId));
    }

    private Group getGroup(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new OperatorBpaException(ResponseCode.RESOURCE_NOT_FOUND, "Could not find group with id: " + groupId));
    }

    @Override
    public AccommodationApplicationDto update(AccommodationApplicationDto accommodationApplicationDto) {
        return null;
    }

    @Override
    public void cancel(Long id) {

    }

    @Override
    public AccommodationApplicationDto get(Long id) {
        return null;
    }

    @Override
    public Collection<AccommodationApplicationDto> getAllFiltered(BaseFilter baseFilter) {
        return null;
    }
}
