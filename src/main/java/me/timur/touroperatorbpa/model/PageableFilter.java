package me.timur.touroperatorbpa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Temurbek Ismoilov on 29/07/23.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class PageableFilter {
    @JsonProperty("page_number")
    protected Integer pageNumber = 0;
    @JsonProperty("page_size")
    protected Integer pageSize = 15;
}
