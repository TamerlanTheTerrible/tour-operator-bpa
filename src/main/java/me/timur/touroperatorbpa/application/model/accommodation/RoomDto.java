package me.timur.touroperatorbpa.application.model.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.timur.touroperatorbpa.domain.entity.Room;
import me.timur.touroperatorbpa.model.enums.RoomType;

import java.math.RoundingMode;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    @NotNull
    @JsonProperty("room_type")
    private RoomType roomType;

    @NotNull
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

    public RoomDto(RoomDto roomDto) {
        this.roomType = roomDto.getRoomType();
        this.requested = roomDto.getRequested();
        this.provided = roomDto.getProvided();
    }

    @Override
    public String toString() {
        return "RoomDto{" +
                "roomType='" + roomType + '\'' +
                ", requested=" + requested +
                '}';
    }
}
