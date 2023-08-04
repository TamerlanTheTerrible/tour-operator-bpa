package me.timur.touroperatorbpa.company.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Temurbek Ismoilov on 03/08/23.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyCreateDto {
    @NotNull @NotBlank
    private String name;

    @NotNull @NotBlank
    private String country;

    @Override
    public String toString() {
        return "CompanyCreateDto{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
