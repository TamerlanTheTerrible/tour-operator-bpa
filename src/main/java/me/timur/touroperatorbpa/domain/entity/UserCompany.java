package me.timur.touroperatorbpa.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.timur.touroperatorbpa.company.model.CompanyCreateDto;

/**
 * Created by Temurbek Ismoilov on 25/07/23.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_company")
public class UserCompany extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @Override
    public String toString() {
        return "UserCompany{" +
                "name='" + name + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
