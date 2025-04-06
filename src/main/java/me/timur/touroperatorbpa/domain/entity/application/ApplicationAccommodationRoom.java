package me.timur.touroperatorbpa.domain.entity.application;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.timur.touroperatorbpa.domain.entity.BaseEntity;
import me.timur.touroperatorbpa.model.enums.RoomType;
import me.timur.touroperatorbpa.application.model.accommodation.RoomDto;

/**
 * Created by Temurbek Ismoilov on 26/07/23.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "application_accommodation_room",
        indexes = {@Index(columnList = "accommodation_application_id")}
)
public class ApplicationAccommodationRoom extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "accommodation_application_id", nullable = false)
    private ApplicationAccommodation application;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type", nullable = false)
    private RoomType roomType;

    @Column(name = "requested", nullable = false)
    private Integer requested;

    @Column(name = "provided", nullable = false)
    private Integer provided;

    public ApplicationAccommodationRoom(RoomDto roomDto) {
        this.roomType = roomDto.getRoomType();
        this.requested = roomDto.getRequested();
        this.provided = 0;
    }

    public ApplicationAccommodationRoom(ApplicationAccommodationRoom applicationAccommodationRoom) {
        this.roomType = applicationAccommodationRoom.getRoomType();
        this.requested = applicationAccommodationRoom.getRequested();
        this.provided = applicationAccommodationRoom.getProvided();
    }

    @Override
    public String toString() {
        return "Room{" +
                "application=" + application +
                ", roomType=" + roomType +
                ", requested=" + requested +
                ", provided=" + provided +
                '}';
    }
}
