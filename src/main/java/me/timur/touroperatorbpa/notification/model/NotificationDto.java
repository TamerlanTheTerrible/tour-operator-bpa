package me.timur.touroperatorbpa.notification.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.timur.touroperatorbpa.domain.entity.Notification;
import me.timur.touroperatorbpa.model.enums.ApplicationType;
import me.timur.touroperatorbpa.model.enums.Role;

/**
 * Created by Temurbek Ismoilov on 31/08/23.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {
    @NotNull
    @JoinColumn(name = "group_id")
    private Long id;

    @NotNull
    @JoinColumn(name = "group_id")
    private Long groupId;

    @NotNull
    @JsonProperty("application_type")
    private ApplicationType applicationType;

    @NotNull
    @JsonProperty("role")
    private Role role;

    @NotNull
    @JsonProperty("message")
    private String message;

    @NotNull
    @JsonProperty("is_read")
    private Boolean isRead = false;

    public NotificationDto(Notification notification) {
        this.id = notification.getId();
        this.groupId = notification.getGroup().getId();
        this.applicationType = notification.getApplicationType();
        // TODO this.role = notification.getRole();
        this.message = notification.getMessage();
        this.isRead = notification.getIsRead();
    }
}
