package me.timur.touroperatorbpa.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Table(name = "application_guide")
public class ApplicationGuide extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "group", nullable = false)
    private Group group;

    @Column(name = "from", nullable = false)
    private LocalDate from;

    @Column(name = "to", nullable = false)
    private LocalDate to;

    @ManyToOne
    @JoinColumn(name = "guide", nullable = false)
    private Guide guide;

    @Column(name = "comment")
    private String comment;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ApplicationStatus status;
}
