package me.timur.touroperatorbpa.domain.repository;

import me.timur.touroperatorbpa.domain.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Temurbek Ismoilov on 26/07/23.
 */

@Repository
public interface GroupRepository extends JpaRepository<Group, Long>, CustomRepository<Group> {
    Long countByArrivalBetween(LocalDateTime from, LocalDateTime to);
    Boolean existsByNumberStartsWithAndArrivalBetween(String number, LocalDateTime from, LocalDateTime to);
    List<Group> findAllByTourOperatorId(Long id);
}
