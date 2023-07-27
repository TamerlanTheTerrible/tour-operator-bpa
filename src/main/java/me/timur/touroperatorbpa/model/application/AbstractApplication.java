package me.timur.touroperatorbpa.model.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.timur.touroperatorbpa.domain.enums.ApplicationStatus;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

@Data
public abstract class AbstractApplication {
    @JsonProperty("group_id")
    public Long groupId;
    @JsonProperty("group_number")
    public String groupNumber;
    public String comment;
    public ApplicationStatus status;
}
