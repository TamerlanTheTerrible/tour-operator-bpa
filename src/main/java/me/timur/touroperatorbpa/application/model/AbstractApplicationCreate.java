package me.timur.touroperatorbpa.application.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

@Data
public abstract class AbstractApplicationCreate implements ApplicationCreate {
    @JsonProperty("group_id")
    public Long groupId;
}
