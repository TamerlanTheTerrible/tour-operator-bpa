package me.timur.touroperatorbpa.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.timur.touroperatorbpa.domain.enums.FlightClass;
import me.timur.touroperatorbpa.domain.enums.ApplicationStatus;

import java.time.LocalDate;

/**
 * Created by Temurbek Ismoilov on 25/07/23.
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "application_flight")
public class ApplicationFlight extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "group", nullable = false)
    private Group group;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "from", nullable = false)
    private String from;

    @Column(name = "to", nullable = false)
    private String to;

    @Enumerated(EnumType.STRING)
    @Column(name = "class")
    private FlightClass ticketClass;

    @Column(name = "time")
    private String time;

    @Column(name = "requested", nullable = false)
    private Integer requested;

    @Column(name = "provided", nullable = false)
    private Integer provided;

    @Column(name = "comment")
    private String comment;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ApplicationStatus status;
}
