package me.timur.touroperatorbpa.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.timur.touroperatorbpa.domain.entity.Role;
import me.timur.touroperatorbpa.domain.entity.User;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 02/08/23.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull
    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("username")
    private String username;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("roles")
    private List<String> roles;

    @JsonProperty("is_active")
    private Boolean isActive;

    public UserDto(User user) {
        this.userId = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
        this.roles = user.getRoles().stream().map(Role::getName).toList();
        this.isActive = user.getIsActive();
        this.phoneNumber = user.getPhoneNumber();
    }
}
