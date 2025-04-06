package me.timur.touroperatorbpa.domain.entity.application;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.timur.touroperatorbpa.domain.entity.Accommodation;
import me.timur.touroperatorbpa.domain.entity.BaseEntity;
import me.timur.touroperatorbpa.domain.entity.Group;
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
@Table(name = "application_accommodation",
    indexes = {@Index(columnList = "group_id,"),
               @Index(columnList = "accommodation"),
               @Index(columnList = "check_in")}
)
public class ApplicationAccommodation extends BaseEntity implements ApplicationEntity {
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
    private List<ApplicationAccommodationRoom> applicationAccommodationRooms = new ArrayList<>();

    @Column(name = "comment")
    private String comment;

    @Column(name = "change_log")
    private String changeLog;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ApplicationStatus status;

    @Column(name = "version")
    private Integer version = 1;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "rating_count", nullable = false)
    private Long ratingCount;

    public ApplicationAccommodation(Group group, Accommodation accommodation, AccommodationApplicationCreateDto.AccommodationItem createDto) {
        this.group = group;
        this.accommodation = accommodation;
        this.checkIn = createDto.getCheckIn();
        this.checkOut = createDto.getCheckOut();
        this.comment = createDto.getComment();
        this.status = ApplicationStatus.ACTIVE;
        this.version = createDto.getVersion() != null ? createDto.getVersion() : 1;
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
        this.applicationAccommodationRooms = new ArrayList<>();
        this.checkIn = application.getCheckIn();
        this.checkOut = application.getCheckOut();
        this.comment = application.getComment();
        this.status = ApplicationStatus.ACTIVE;
        this.version = application.getVersion() + 1;
    }

    public void addRoom(ApplicationAccommodationRoom applicationAccommodationRoom) {
        applicationAccommodationRoom.setApplication(this);
        applicationAccommodationRooms.add(applicationAccommodationRoom);
    }

    public void addRooms(List<ApplicationAccommodationRoom> applicationAccommodationRooms) {
        applicationAccommodationRooms.forEach(this::addRoom);
    }

    @Override
    public int version() {
        return this.version;
    }

    @Override
    public ApplicationStatus status() {
        return this.status;
    }
}
