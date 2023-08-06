package me.timur.touroperatorbpa.security.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Created by Temurbek Ismoilov on 06/08/23.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInDto {
    @NonNull @NotBlank
    private String email;
    @NonNull @NotBlank
    private String password;
}
