package me.timur.touroperatorbpa.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 02/08/23.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("initials")
    private String initials;

    @JsonProperty("roles")
    private List<String> roles;

}
