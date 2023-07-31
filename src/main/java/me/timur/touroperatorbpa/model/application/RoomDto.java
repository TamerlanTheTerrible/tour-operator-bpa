package me.timur.touroperatorbpa.model.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.timur.touroperatorbpa.domain.entity.Room;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

@Data
public class RoomDto {
    @JsonProperty("room_type")
    private String roomType;

    @JsonProperty("requested")
    private Integer requested;

    @JsonProperty("provided")
    private Integer provided;

    public RoomDto(Room room) {
        this.roomType = room.getRoomType();
        this.requested = room.getRequested();
        this.provided = room.getProvided();
    }

    @Override
    public String toString() {
        return "RoomDto{" +
                "roomType='" + roomType + '\'' +
                ", requested=" + requested +
                '}';
    }
}
