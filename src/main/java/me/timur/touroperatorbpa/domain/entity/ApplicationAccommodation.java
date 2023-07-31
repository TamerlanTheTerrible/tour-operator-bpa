package me.timur.touroperatorbpa.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.timur.touroperatorbpa.domain.enums.ApplicationStatus;
import me.timur.touroperatorbpa.model.application.impl.AccommodationApplicationCreateDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Temurbek Ismoilov on 25/07/23.
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "application_accommodation")
public class ApplicationAccommodation extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @ManyToOne
    @JoinColumn(name = "accommodation", nullable = false)
    private Accommodation accommodation;

    @Column(name = "check_in", nullable = false)
    private LocalDateTime checkIn;

    @Column(name = "check_out", nullable = false)
    private LocalDateTime checkOut;

    @OneToMany(mappedBy = "application", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Room> rooms = new ArrayList<>();

    @Column(name = "comment")
    private String comment;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ApplicationStatus status;

    public ApplicationAccommodation(Group group, Accommodation accommodation, AccommodationApplicationCreateDto.AccommodationItem createDto) {
        this.group = group;
        this.accommodation = accommodation;
        this.checkIn = createDto.getCheckIn();
        this.checkOut = createDto.getCheckOut();
        this.comment = createDto.getComment();
        this.status = ApplicationStatus.ACTIVE;
    }

    public void addRoom(Room room) {
        room.setApplication(this);
        rooms.add(room);
    }

    public void addRooms(List<Room> rooms) {
        rooms.forEach(this::addRoom);
    }
}
