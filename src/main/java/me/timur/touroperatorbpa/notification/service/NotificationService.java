package me.timur.touroperatorbpa.notification.service;

import me.timur.touroperatorbpa.model.enums.ApplicationType;
import me.timur.touroperatorbpa.model.enums.RoleName;
import me.timur.touroperatorbpa.notification.model.NotificationCreateDto;
import me.timur.touroperatorbpa.notification.model.NotificationDto;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 30/08/23.
 */

public interface NotificationService {
    List<NotificationDto> create(NotificationCreateDto createDto, List<RoleName> roleNames);
    NotificationDto create(NotificationCreateDto createDto, RoleName roleName);
    List<NotificationDto> getAllByGroupAndApplicaitonType(Long groupId, ApplicationType type);
    void markAsRead(Long id);
}
