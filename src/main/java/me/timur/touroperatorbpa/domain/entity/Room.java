package me.timur.touroperatorbpa.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JoinColumn(name = "application", nullable = false)
    private ApplicationAccommodation application;

    @Column(name = "group_number", nullable = false)
    private String groupNumber;

    @Column(name = "room_type", nullable = false)
    private String roomType;

    @Column(name = "requested", nullable = false)
    private Integer requested;

    @Column(name = "provided", nullable = false)
    private Integer provided;
}
