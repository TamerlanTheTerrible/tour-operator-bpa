package me.timur.touroperatorbpa.application.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.timur.touroperatorbpa.model.enums.ApplicationStatus;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

@Data
public abstract class AbstractApplication implements Application {
    @JsonProperty("group_id")
    public Long groupId;
    @JsonProperty("group_number")
    public String groupNumber;
    public ApplicationStatus status;

    public ApplicationStatus getOverallStatus(List<ApplicationStatus> statusList) {
        return statusList.stream().allMatch(s -> s == ApplicationStatus.CONFIRMED)
                ? ApplicationStatus.CONFIRMED
                : statusList.stream().allMatch(s -> s == ApplicationStatus.CANCELLED)
                ? ApplicationStatus.CANCELLED
                : ApplicationStatus.ACTIVE;
    }
}
