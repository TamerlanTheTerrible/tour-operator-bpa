package me.timur.touroperatorbpa.domain.repository;

import me.timur.touroperatorbpa.domain.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * Created by Temurbek Ismoilov on 26/07/23.
 */

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Long countByAndArrivalBetween(LocalDateTime from, LocalDateTime to);
}
