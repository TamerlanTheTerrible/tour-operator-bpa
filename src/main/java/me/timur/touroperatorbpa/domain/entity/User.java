package me.timur.touroperatorbpa.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.timur.touroperatorbpa.user.model.UserCreateDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Temurbek Ismoilov on 25/07/23.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_company_id", nullable = false)
    private UserCompany userCompanyId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name= "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name= "phone_number")
    private String phoneNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "name", nullable = false))
    private List<Role> roles;

    @Column(name = "is_active")
    private Boolean isActive = true;

    public User(UserCreateDto userCreateDto, String password, List<Role> roles) {
        this.firstName = userCreateDto.getFirstName();
        this.lastName = userCreateDto.getLastName();
        this.username = userCreateDto.getUsername();
        this.phoneNumber = userCreateDto.getPhoneNumber();
        this.roles = roles;
        this.password = password;
        this.isActive = true;
    }

    public Set<String> getRoleNames() {
        return roles.stream().map(Role::getName).collect(Collectors.toSet());
    }
}
