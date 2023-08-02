package me.timur.touroperatorbpa.application.model.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by Temurbek Ismoilov on 28/07/23.
 */

@Data
public class AccommodationDto {
    private String name;
    @JsonProperty("location_id")
    private Long locationId;
    @JsonProperty("location_name")
    private String locationName;
}
