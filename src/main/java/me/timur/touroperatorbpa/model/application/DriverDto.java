package me.timur.touroperatorbpa.model.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by Temurbek Ismoilov on 28/07/23.
 */

@Data
public class DriverDto {
    @JsonProperty("driver_id")
    private Long driverId;

    @JsonProperty("driver_name")
    private String driverName;

    @JsonProperty("driver_phone")
    private String driverPhone;

    @JsonProperty("comment")
    private String comment;
}
