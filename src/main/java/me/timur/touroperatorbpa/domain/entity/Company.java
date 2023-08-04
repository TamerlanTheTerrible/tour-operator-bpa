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
@Table(name = "company")
public class Company extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "country", nullable = false)
    private String country;

    public Company(CompanyCreateDto createDto) {
        this.name = createDto.getName();
        this.country = createDto.getCountry();
    }
}
