package me.timur.touroperatorbpa.domain.repository;

import me.timur.touroperatorbpa.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Temurbek Ismoilov on 26/07/23.
 */

public interface UserRepository extends JpaRepository<User, Long> {
}
