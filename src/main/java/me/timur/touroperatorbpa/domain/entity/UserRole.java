package me.timur.touroperatorbpa.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.timur.touroperatorbpa.model.enums.Role;

/**
 * Created by Temurbek Ismoilov on 05/02/25.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "user_role",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role"})
)
public class UserRole extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRole userRole)) return false;

        if (!getUser().equals(userRole.getUser())) return false;
        return getRole() == userRole.getRole();
    }

    @Override
    public int hashCode() {
        int result = getUser().hashCode();
        result = 31 * result + getRole().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "user=" + user.getUsername() +
                ", userId=" + user.getId() +
                ", role=" + role +
                ", isActive=" + isActive +
                '}';
    }
}
