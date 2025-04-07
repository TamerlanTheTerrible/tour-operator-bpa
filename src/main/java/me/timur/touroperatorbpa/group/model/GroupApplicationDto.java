package me.timur.touroperatorbpa.group.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.timur.touroperatorbpa.domain.entity.application.ApplicationEntity;
import me.timur.touroperatorbpa.model.enums.ApplicationStatus;
import me.timur.touroperatorbpa.model.enums.ApplicationType;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 07/04/25.
 */

@Data
@NoArgsConstructor
public class GroupApplicationDto {
    @JsonProperty("application_id")
    private Long applicationId;

    @JsonProperty("application_type")
    private ApplicationType applicationType;

    @JsonProperty("application_status")
    private ApplicationStatus status;

    public GroupApplicationDto(ApplicationEntity application) {
        if (application == null) return;
        this.applicationId = application.id();
        this.applicationType = application.type();
        this.status = application.status();
    }
}
