package me.timur.touroperatorbpa.domain.repository;

import me.timur.touroperatorbpa.domain.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Temurbek Ismoilov on 08/02/25.
 */

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
