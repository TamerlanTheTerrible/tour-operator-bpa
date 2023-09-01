package me.timur.touroperatorbpa.notification.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.timur.touroperatorbpa.domain.entity.ApplicationChangelog;
import me.timur.touroperatorbpa.model.enums.ApplicationType;

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
    @JsonProperty("version")
    private Integer version;

    @NotNull
    @JsonProperty("change")
    private String change;

    @NotNull
    @JsonProperty("is_read")
    private Boolean isRead = false;

    public NotificationDto(ApplicationChangelog changelog) {
        this.id = changelog.getId();
        this.groupId = changelog.getGroup().getId();
        this.applicationType = changelog.getApplicationType();
        this.version = changelog.getVersion();
        this.change = changelog.getChange();
        this.isRead = changelog.getIsRead();
    }
}
