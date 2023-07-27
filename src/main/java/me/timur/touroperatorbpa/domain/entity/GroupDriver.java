package me.timur.touroperatorbpa.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "group_driver")
public class GroupDriver extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "transport_application", nullable = false)
    private ApplicationTransport application;

    @Column(name = "from", nullable = false)
    private LocalDate from;

    @Column(name = "to", nullable = false)
    private LocalDate to;

    @ManyToOne
    @JoinColumn(name = "driver", nullable = false)
    private Driver driver;

    @Column(name = "comment")
    private String comment;
}
