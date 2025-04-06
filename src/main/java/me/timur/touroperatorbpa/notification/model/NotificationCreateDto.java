package me.timur.touroperatorbpa.notification.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.timur.touroperatorbpa.model.enums.ApplicationType;
import me.timur.touroperatorbpa.model.enums.Role;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 30/08/23.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationCreateDto {
    private Long senderId;
    private Long groupId;
    private List<Role> receiverRoles;
    private Long applicationId;
    private String message;
}
