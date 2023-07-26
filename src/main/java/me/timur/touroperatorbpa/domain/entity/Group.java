package me.timur.touroperatorbpa.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.timur.touroperatorbpa.domain.enums.GroupStatus;

import java.time.LocalDateTime;

/**
 * Created by Temurbek Ismoilov on 25/07/23.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "group")
public class Group extends BaseEntity {
    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "country", nullable = false)
    private String country;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "size", nullable = false)
    private Integer size;

    @Column(name = "tour_leader_amount", nullable = false)
    private Integer tourLeaderAmount;

    @Column(name = "arrival", nullable = false)
    private LocalDateTime arrival;

    @Column(name = "departure")
    private LocalDateTime departure;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "tour_operator_id", nullable = false)
    private User tourOperator;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private GroupStatus status;
}
