package me.timur.touroperatorbpa.domain.entity.application;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.timur.touroperatorbpa.domain.entity.Accommodation;
import me.timur.touroperatorbpa.domain.entity.BaseEntity;
import me.timur.touroperatorbpa.domain.entity.Group;
import me.timur.touroperatorbpa.domain.entity.Room;
import me.timur.touroperatorbpa.model.enums.ApplicationStatus;
import me.timur.touroperatorbpa.application.model.accommodation.AccommodationApplicationCreateDto;
import me.timur.touroperatorbpa.application.model.accommodation.AccommodationApplicationDto;

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

//    @OneToMany(mappedBy = "application", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @OneToMany(mappedBy = "application", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<Room> rooms = new ArrayList<>();

    @Column(name = "comment")
    private String comment;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ApplicationStatus status;

    @Column(name = "version")
    private Integer version = 1;

    public ApplicationAccommodation(Group group, Accommodation accommodation, AccommodationApplicationCreateDto.AccommodationItem createDto) {
        this.group = group;
        this.accommodation = accommodation;
        this.checkIn = createDto.getCheckIn();
        this.checkOut = createDto.getCheckOut();
        this.comment = createDto.getComment();
        this.status = ApplicationStatus.ACTIVE;
        this.version = 1;
    }

    public ApplicationAccommodation(Group group, Accommodation accommodation, AccommodationApplicationDto.AccommodationItem dto, int version) {
        this.group = group;
        this.accommodation = accommodation;
        this.checkIn = dto.getCheckIn();
        this.checkOut = dto.getCheckOut();
        this.comment = dto.getComment();
        this.status = ApplicationStatus.ACTIVE;
        this.version = version;
    }

    public ApplicationAccommodation(ApplicationAccommodation application) {
        this.group = application.getGroup();
        this.accommodation = application.getAccommodation();
        this.rooms = new ArrayList<>();
        this.checkIn = application.getCheckIn();
        this.checkOut = application.getCheckOut();
        this.comment = application.getComment();
        this.status = ApplicationStatus.ACTIVE;
        this.version = application.getVersion() + 1;
    }

    public void addRoom(Room room) {
        room.setApplication(this);
        rooms.add(room);
    }

    public void addRooms(List<Room> rooms) {
        rooms.forEach(this::addRoom);
    }
}
