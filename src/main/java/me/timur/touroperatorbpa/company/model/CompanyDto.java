package me.timur.touroperatorbpa.company.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.timur.touroperatorbpa.domain.entity.PartnerCompany;
import me.timur.touroperatorbpa.model.enums.Country;

/**
 * Created by Temurbek Ismoilov on 03/08/23.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    @NotNull
    private Long id;
    private String name;
    private Country country;

    public CompanyDto(PartnerCompany partnerCompany) {
        this.id = partnerCompany.getId();
        this.name = partnerCompany.getName();
        this.country = partnerCompany.getCountry();
    }

    @Override
    public String toString() {
        return "CompanyDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
