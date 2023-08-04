package me.timur.touroperatorbpa.accommodation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.timur.touroperatorbpa.domain.entity.Accommodation;

/**
 * Created by Temurbek Ismoilov on 28/07/23.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationDto {
    @NotNull
    @JsonProperty("accommodation_id")
    private Long accommodationId;
    @JsonProperty("accommodation_name")
    private String accommodationName;
    @JsonProperty("location_name")
    private String locationName;

    public AccommodationDto(Accommodation accommodation) {
        this.accommodationId = accommodation.getId();
        this.accommodationName = accommodation.getName();
        this.locationName = accommodation.getLocation().getName();
    }

    @Override
    public String toString() {
        return "AccommodationDto{" +
                "accommodationId=" + accommodationId +
                ", accommodationName='" + accommodationName + '\'' +
                ", locationName='" + locationName + '\'' +
                '}';
    }
}
