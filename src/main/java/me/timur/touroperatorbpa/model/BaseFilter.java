package me.timur.touroperatorbpa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by Temurbek Ismoilov on 29/07/23.
 */

@Data
public abstract class BaseFilter {
    @JsonProperty("page_number")
    protected Integer pageNumber = 0;
    @JsonProperty("page_size")
    protected Integer pageSize = 15;
}
