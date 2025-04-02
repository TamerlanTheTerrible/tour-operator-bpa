package me.timur.touroperatorbpa.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.timur.touroperatorbpa.model.enums.Role;
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
@Table(
        name = "users",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_company_id", "username"}),
        indexes = @Index(columnList = "user_company_id")
)
public class User extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_company_id", nullable = false)
    private UserCompany userCompany;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name= "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name= "phone_number")
    private String phoneNumber;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserRole> roles;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    public User(UserCreateDto userCreateDto, String password) {
        this.firstName = userCreateDto.getFirstName();
        this.lastName = userCreateDto.getLastName();
        this.username = userCreateDto.getUsername();
        this.phoneNumber = userCreateDto.getPhoneNumber();
        this.password = password;
        this.isActive = true;
    }

    public Set<Role> getRoleNames() {
        return roles.stream().map(UserRole::getRole).collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return "User{" +
                "userCompany=" + userCompany.getName() +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", roles=" + getRoleNames() +
                ", isActive=" + isActive +
                '}';
    }
}
