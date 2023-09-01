package me.timur.touroperatorbpa.notification.controller;

import lombok.RequiredArgsConstructor;
import me.timur.touroperatorbpa.model.enums.ApplicationType;
import me.timur.touroperatorbpa.model.response.BaseResponse;
import me.timur.touroperatorbpa.model.response.NoopDTO;
import me.timur.touroperatorbpa.notification.model.NotificationDto;
import me.timur.touroperatorbpa.notification.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 01/09/23.
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/group/{groupId}/application-type/{type}")
    public BaseResponse<List<NotificationDto>> getAllByGroupAndType(@PathVariable Long groupId, @PathVariable ApplicationType type) {
        return BaseResponse.ok(notificationService.getAllByGroupAndApplicaitonType(groupId, type));
    }

    @PutMapping("/{id}/read")
    public BaseResponse<NoopDTO> markAsRead(@PathVariable Long id) {
        notificationService.markAsRead(id);
        return BaseResponse.ok();
    }
}
