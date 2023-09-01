package me.timur.touroperatorbpa.notification.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.timur.touroperatorbpa.domain.entity.ApplicationChangelog;
import me.timur.touroperatorbpa.domain.entity.Group;
import me.timur.touroperatorbpa.domain.repository.ApplicationChangeLogRepository;
import me.timur.touroperatorbpa.domain.repository.GroupRepository;
import me.timur.touroperatorbpa.exception.ClientException;
import me.timur.touroperatorbpa.exception.InternalException;
import me.timur.touroperatorbpa.model.enums.ApplicationType;
import me.timur.touroperatorbpa.model.enums.ResponseCode;
import me.timur.touroperatorbpa.notification.model.NotificationCreateDto;
import me.timur.touroperatorbpa.notification.model.NotificationDto;
import me.timur.touroperatorbpa.notification.service.NotificationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 31/08/23.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final ApplicationChangeLogRepository applicationChangeLogRepository;
    private final GroupRepository groupRepository;

    @Override
    public NotificationDto create(NotificationCreateDto createDto) {
        var changelog = applicationChangeLogRepository.save(new ApplicationChangelog(
                createDto,
                getGroup(createDto.getGroupId())
        ));

        return new NotificationDto(changelog);
    }

    @Override
    public List<NotificationDto> getAllByGroupAndApplicaitonType(Long groupId, ApplicationType type) {
        var group = getGroup(groupId);
        return applicationChangeLogRepository.findAllByGroupAndAndApplicationTypeOrderByIdDesc(group.getId(), type.name())
                .stream()
                .map(NotificationDto::new)
                .toList();
    }

    @Override
    public void markAsRead(Long id) {
        var changelog = applicationChangeLogRepository.findById(id)
                .orElseThrow(() -> new ClientException(ResponseCode.RESOURCE_NOT_FOUND, "Could not find notification with id: " + id));
        changelog.setIsRead(true);
        applicationChangeLogRepository.save(changelog);
    }

    private Group getGroup(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new InternalException(ResponseCode.RESOURCE_NOT_FOUND, "Could not find group with id: " + groupId));
    }
}
