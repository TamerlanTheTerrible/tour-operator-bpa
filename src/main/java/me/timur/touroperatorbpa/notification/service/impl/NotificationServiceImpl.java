package me.timur.touroperatorbpa.notification.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.timur.touroperatorbpa.domain.entity.Notification;
import me.timur.touroperatorbpa.domain.entity.Group;
import me.timur.touroperatorbpa.domain.repository.NotificationRespository;
import me.timur.touroperatorbpa.domain.repository.GroupRepository;
import me.timur.touroperatorbpa.exception.ClientException;
import me.timur.touroperatorbpa.exception.InternalException;
import me.timur.touroperatorbpa.model.enums.ApplicationType;
import me.timur.touroperatorbpa.model.enums.ResponseCode;
import me.timur.touroperatorbpa.model.enums.Role;
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

    private final NotificationRespository notificationRespository;
    private final GroupRepository groupRepository;

    @Override
    public List<NotificationDto> create(NotificationCreateDto createDto, List<Role> roles) {
        //TODO fix null
        final List<Notification> notifications = roles.stream().map(roleName -> new Notification(createDto, null, null)).toList();
        notificationRespository.saveAll(notifications);
        return notifications.stream().map(NotificationDto::new).toList();
    }

    @Override
    public NotificationDto create(NotificationCreateDto createDto, Role role) {
        //TODO fix null
        var notification = new Notification(createDto, null, null);
        notificationRespository.save(notification);
        return new NotificationDto(notification);
    }

    @Override
    public List<NotificationDto> getAllByGroupAndApplicaitonType(Long groupId, ApplicationType type) {
        var group = getGroup(groupId);
        return notificationRespository.findAllByGroupAndAndApplicationTypeOrderByIdDesc(group.getId(), type.name())
                .stream()
                .map(NotificationDto::new)
                .toList();
    }

    @Override
    public void markAsRead(Long id) {
        var changelog = notificationRespository.findById(id)
                .orElseThrow(() -> new ClientException(ResponseCode.RESOURCE_NOT_FOUND, "Could not find notification with id: " + id));
        changelog.setIsRead(true);
        notificationRespository.save(changelog);
    }

    private Group getGroup(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new InternalException(ResponseCode.RESOURCE_NOT_FOUND, "Could not find group with id: " + groupId));
    }
}
