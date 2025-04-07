package me.timur.touroperatorbpa.domain.entity.application;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.timur.touroperatorbpa.domain.entity.BaseEntity;
import me.timur.touroperatorbpa.domain.entity.Group;
import me.timur.touroperatorbpa.model.enums.ApplicationStatus;
import me.timur.touroperatorbpa.model.enums.ApplicationType;
import me.timur.touroperatorbpa.model.enums.TrainClass;

import java.time.LocalDate;

/**
 * Created by Temurbek Ismoilov on 25/07/23.
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "application_train", indexes = {@Index(columnList = "group_id")})
public class ApplicationTrain extends BaseEntity implements ApplicationEntity {
    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "from_location", nullable = false)
    private String fromLocation;

    @Column(name = "to_location", nullable = false)
    private String toLocation;

    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_class")
    private TrainClass ticketClass;

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

    @Override
    public long id() {
        return getId();
    }

    @Override
    public ApplicationType type() {
        return ApplicationType.TRAIN;
    }

    @Override
    public ApplicationStatus status() {
        return getStatus();
    }
}
