package me.timur.touroperatorbpa.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Table(name = "room")
public class Room extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "accommodation_application", nullable = false)
    private ApplicationAccommodation application;

    @Column(name = "group_number", nullable = false)
    private String groupNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type", nullable = false)
    private RoomType roomType;

    @Column(name = "requested", nullable = false)
    private Integer requested;

    @Column(name = "provided", nullable = false)
    private Integer provided;

    public Room(ApplicationAccommodation application, RoomDto roomDto) {
        this.application = application;
        this.groupNumber = application.getGroup().getNumber();
        this.roomType = roomDto.getRoomType();
        this.requested = roomDto.getRequested();
        this.provided = 0;
    }
}
