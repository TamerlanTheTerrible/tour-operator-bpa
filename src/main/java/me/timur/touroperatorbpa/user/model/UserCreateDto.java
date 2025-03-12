package me.timur.touroperatorbpa.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.timur.touroperatorbpa.model.enums.Role;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 02/08/23.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {

    @NotNull(message = "First name must not be null")
    @JsonProperty("first_name")
    private String firstName;

    @NotNull(message = "Last name must not be null")
    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("username")
    private String username;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("password")
    private String password;

    @NotNull(message = "Roles must not be null") @NotEmpty
    @JsonProperty("roles")
    private List<Role> roles;


}
