package me.timur.touroperatorbpa.model.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

@Data
public class RoomCreateDto {
    @JsonProperty("room_type")
    private String roomType;

    @JsonProperty("requested")
    private Integer requested;

    @Override
    public String toString() {
        return "RoomCreateDto{" +
                "roomType='" + roomType + '\'' +
                ", requested=" + requested +
                '}';
    }
}
