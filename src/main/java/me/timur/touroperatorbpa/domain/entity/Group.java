package me.timur.touroperatorbpa.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.timur.touroperatorbpa.model.enums.Country;
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
@Table(name = "group",
    indexes = {@Index(name = "idx_group_tour_operator_id", columnList = "tour_operator_id"),
            @Index(name = "idx_group_country", columnList = "country"),
            @Index(name = "idx_group_partner_company_id", columnList = "partner_company_id"),
            @Index(name = "idx_group_arrival_time", columnList = "arrival_time"),
            @Index(name = "idx_group_status", columnList = "status")}
)
public class Group extends BaseEntity {
    @Column(name = "number")
    private String number;

    @ManyToOne
    @JoinColumn(name = "tour_operator_id", nullable = false)
    private User tourOperator;

    @Enumerated(EnumType.STRING)
    @Column(name = "country", nullable = false)
    private Country country;

    @ManyToOne
    @JoinColumn(name = "partner_company_id", nullable = false)
    private PartnerCompany partnerCompany;

    @Column(name = "size", nullable = false)
    private Integer size;

    @Column(name = "tour_leader_count", nullable = false)
    private Integer tourLeaderAmount;

    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "comment")
    private String comment;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private GroupStatus status = GroupStatus.ACTIVE;

    public Group(GroupCreateDto dto, User tourOperator, PartnerCompany partnerCompany) {
        this.tourOperator = tourOperator;
        this.partnerCompany = partnerCompany;
        this.number = dto.getNumber();
        this.country = dto.getCountry();
        this.size = dto.getSize();
        this.tourLeaderAmount = dto.getTourLeaderCount();
        this.arrivalTime = dto.getArrival();
        this.departureTime = dto.getDeparture();
        this.comment = dto.getComment();
    }
}
