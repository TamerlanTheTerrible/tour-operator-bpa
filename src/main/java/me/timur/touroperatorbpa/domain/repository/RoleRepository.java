package me.timur.touroperatorbpa.domain.repository;

import me.timur.touroperatorbpa.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by Temurbek Ismoilov on 26/07/23.
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAllByNameIn(Collection<String> names);
}
