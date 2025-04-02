package me.timur.touroperatorbpa.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Temurbek Ismoilov on 25/07/23.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "location",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "user_company_id"}),
        indexes = @Index(columnList = "user_company_id")
)
public class Location extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_company_id", nullable = false)
    private UserCompany userCompany;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;


    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", userCompany=" + userCompany.getName() +
                ", userCompanyId=" + userCompany.getId() +
                ", isActive=" + isActive +
                '}';
    }
}
