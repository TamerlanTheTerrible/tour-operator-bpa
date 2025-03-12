package me.timur.touroperatorbpa.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.timur.touroperatorbpa.model.enums.GroupStatus;
import me.timur.touroperatorbpa.group.model.GroupCreateDto;

import java.time.LocalDateTime;

/**
 * Created by Temurbek Ismoilov on 25/07/23.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "groups")
public class Group extends BaseEntity {
    @Column(name = "number", nullable = false)
    private String number;

    @ManyToOne
    @JoinColumn(name = "tour_operator_id", nullable = false)
    private User tourOperator;

    @Column(name = "country", nullable = false)
    private String country;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private PartnerCompany partnerCompany;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private GroupStatus status;

    public Group(GroupCreateDto dto, User tourOperator, PartnerCompany partnerCompany) {
        this.tourOperator = tourOperator;
        this.partnerCompany = partnerCompany;
        this.number = dto.getNumber();
        this.country = dto.getCountry();
        this.size = dto.getSize();
        this.tourLeaderAmount = dto.getTourLeaderAmount();
        this.arrival = dto.getArrival();
        this.departure = dto.getDeparture();
        this.comment = dto.getComment();
        this.status = GroupStatus.ACTIVE;
    }
}
