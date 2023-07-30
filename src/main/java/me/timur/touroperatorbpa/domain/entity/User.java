package me.timur.touroperatorbpa.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name= "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "initial", unique = true)
    private String initial;

    @Column(name= "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "role", nullable = false)
    private Role role;
}
