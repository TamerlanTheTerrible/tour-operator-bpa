package me.timur.touroperatorbpa.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.timur.touroperatorbpa.company.model.CompanyCreateDto;
import me.timur.touroperatorbpa.model.enums.Country;

/**
 * Created by Temurbek Ismoilov on 25/07/23.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "partner_company",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_company_id", "name", "country"})
)
public class PartnerCompany extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_company_id", nullable = false)
    private UserCompany userCompany;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "country", nullable = false)
    private Country country;

    @Column(name = "is_active")
    private boolean isActive = true;

    public PartnerCompany(CompanyCreateDto createDto) {
        this.name = createDto.getName();
        this.country = createDto.getCountry();
    }

    @Override
    public String toString() {
        return "PartnerCompany{" +
                "country=" + country +
                ", name='" + name + '\'' +
                ", userCompany=" + userCompany.getName() +
                ", userCompanyId=" + userCompany.getId() +
                ", isActive=" + isActive +
                '}';
    }
}
