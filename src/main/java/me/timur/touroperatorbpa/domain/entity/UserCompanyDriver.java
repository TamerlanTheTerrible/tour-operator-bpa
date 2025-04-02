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
@Table(name = "user_company_driver",
        indexes = {@Index(name = "idx_user_company_driver_driver_id", columnList = "driver_id"),
                @Index(name = "idx_user_company_driver_user_company_id", columnList = "user_company_id")}
)
public class UserCompanyDriver extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_company_id", nullable = false)
    private UserCompany userCompany;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "rating_count", nullable = false)
    private Long ratingCount;
}
