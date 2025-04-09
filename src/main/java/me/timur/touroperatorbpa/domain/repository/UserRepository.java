package me.timur.touroperatorbpa.domain.repository;

import me.timur.touroperatorbpa.domain.entity.User;
import me.timur.touroperatorbpa.domain.entity.UserCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Temurbek Ismoilov on 26/07/23.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findAllByUserCompany(UserCompany userCompany);
}
