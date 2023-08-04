package me.timur.touroperatorbpa.accommodation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Temurbek Ismoilov on 28/07/23.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationCreateDto {
    @NotNull @NotBlank
    @JsonProperty("accommodation_name")
    private String accommodationName;
    @JsonProperty("location_name")
    private String locationName;

    @Override
    public String toString() {
        return "AccommodationCreateDto{" +
                "accommodationName='" + accommodationName + '\'' +
                ", locationName='" + locationName + '\'' +
                '}';
    }
}
