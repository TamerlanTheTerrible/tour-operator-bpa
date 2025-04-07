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

import java.time.LocalDate;

/**
 * Created by Temurbek Ismoilov on 25/07/23.
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "application_transport", indexes = @Index(columnList = "group_id"))
public class ApplicationTransport extends BaseEntity implements ApplicationEntity {
    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "direction", nullable = false)
    private String direction;

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
        return ApplicationType.TRANSPORT;
    }

    @Override
    public ApplicationStatus status() {
        return getStatus();
    }
}
