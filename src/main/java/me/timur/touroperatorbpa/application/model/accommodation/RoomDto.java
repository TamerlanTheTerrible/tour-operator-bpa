package me.timur.touroperatorbpa.application.model.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.timur.touroperatorbpa.domain.entity.Room;
import me.timur.touroperatorbpa.domain.enums.RoomType;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

@Data
public class RoomDto {
    @JsonProperty("room_type")
    private RoomType roomType;

    @JsonProperty("requested")
    private Integer requested;

    @JsonProperty("provided")
    private Integer provided;

    public RoomDto(Room room) {
        this.roomType = room.getRoomType();
        this.requested = room.getRequested();
        this.provided = room.getProvided();
    }

    public RoomDto(RoomType roomType, int requested) {
        this.roomType = roomType;
        this.requested = requested;
        this.provided = 0;
    }

    @Override
    public String toString() {
        return "RoomDto{" +
                "roomType='" + roomType + '\'' +
                ", requested=" + requested +
                '}';
    }
}
