package me.timur.touroperatorbpa.notification.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.timur.touroperatorbpa.model.enums.ApplicationType;

/**
 * Created by Temurbek Ismoilov on 30/08/23.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationCreateDto {
    @NotNull
    @JoinColumn(name = "group_id")
    private Long groupId;

    @NotNull
    @JsonProperty("application_type")
    private ApplicationType applicationType;

    @NotNull
    @JsonProperty("version")
    private Integer version;

    @NotNull
    @JsonProperty("change")
    private String change;
}
