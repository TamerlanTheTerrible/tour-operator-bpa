package me.timur.touroperatorbpa.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class PasswordDto {
    @NotNull(message = "Password must not be null") @NotBlank
    @JsonProperty(value = "password")
    private String password;
}
