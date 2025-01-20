package me.timur.touroperatorbpa.accommodation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.timur.touroperatorbpa.model.enums.AccommodationCategory;

/**
 * Created by Temurbek Ismoilov on 28/07/23.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationCreateDto {
    @NotBlank(message = "Accommodation name must not be null")
    @JsonProperty("accommodation_name")
    private String accommodationName;

    @NotBlank(message = "Location name must not be blank")
    @JsonProperty("location_name")
    private String locationName;

    @NotBlank(message = "Accommodation category id must not be blank")
    @JsonProperty("category")
    private AccommodationCategory category;

    @JsonProperty("details")
    private String details;

    @Override
    public String toString() {
        return "AccommodationCreateDto{" +
                "accommodationName='" + accommodationName + '\'' +
                ", locationName='" + locationName + '\'' +
                '}';
    }
}
