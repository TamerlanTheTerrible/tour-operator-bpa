package me.timur.touroperatorbpa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by Temurbek Ismoilov on 28/07/23.
 */

@Data
public class AccommodationCreateDto {
    private String name;
    @JsonProperty("location_id")
    private Long locationId;
}
