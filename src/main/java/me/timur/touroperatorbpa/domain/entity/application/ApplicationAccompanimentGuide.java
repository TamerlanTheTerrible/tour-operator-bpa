package me.timur.touroperatorbpa.domain.entity.application;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.timur.touroperatorbpa.domain.entity.BaseEntity;
import me.timur.touroperatorbpa.domain.entity.UserCompanyGuide;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "application_accompaniment_guide",
    indexes = {@Index(columnList = "application_accompaniment_id"), @Index(columnList = "user_company_guide_id")}
)
public class ApplicationAccompanimentGuide extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "application_accompaniment_id", nullable = false)
    private ApplicationAccompaniment application;

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
