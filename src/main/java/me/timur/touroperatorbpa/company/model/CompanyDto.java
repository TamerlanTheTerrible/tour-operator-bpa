package me.timur.touroperatorbpa.company.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.timur.touroperatorbpa.domain.entity.Company;

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
    private String country;

    public CompanyDto(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.country = company.getCountry();
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
