package me.timur.touroperatorbpa.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.timur.touroperatorbpa.domain.entity.application.ApplicationGuide;
import me.timur.touroperatorbpa.domain.entity.application.ApplicationTransport;

import java.time.LocalDate;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "group_guide",
    indexes = {@Index(name = "idx_group_guide_guide_application_id", columnList = "guide_application_id"),
            @Index(name = "idx_group_guide_user_company_guide_id", columnList = "user_company_guide_id")}
)
public class GroupGuide extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "guide_application_id", nullable = false)
    private ApplicationGuide application;

    @ManyToOne
    @JoinColumn(name = "user_company_guide_id", nullable = false)
    private UserCompanyGuide guide;

    @Column(name = "comment")
    private String comment;

    @Column(name = "is_active")
    private boolean isActive = true;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "rating_count", nullable = false)
    private Long ratingCount;
}
