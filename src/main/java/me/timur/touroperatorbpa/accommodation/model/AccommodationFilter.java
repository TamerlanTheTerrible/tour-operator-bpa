package me.timur.touroperatorbpa.accommodation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.timur.touroperatorbpa.model.PageableFilter;

/**
 * Created by Temurbek Ismoilov on 20/08/23.
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class AccommodationFilter extends PageableFilter {
    private String location;

    public AccommodationFilter(String location) {
        this.location = location;
        this.setPageSize(1000);
    }
}
