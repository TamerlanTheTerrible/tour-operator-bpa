package me.timur.touroperatorbpa.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.timur.touroperatorbpa.model.enums.Language;


/**
 * Created by Temurbek Ismoilov on 26/07/23.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_company_guide",
        indexes = {@Index(name = "idx_user_company_guide_guide_id", columnList = "guide_id"),
                @Index(name = "idx_user_company_guide_user_company_id", columnList = "user_company_id")}
)
public class UserCompanyGuide extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_company_id", nullable = false)
    private UserCompany userCompany;

    @ManyToOne
    @JoinColumn(name = "guide_id", nullable = false)
    private Guide guide;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "rating_count", nullable = false)
    private Long ratingCount;
}
