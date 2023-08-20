package me.timur.touroperatorbpa.domain.repository;

import me.timur.touroperatorbpa.domain.entity.application.ApplicationAccommodation;
import me.timur.touroperatorbpa.model.enums.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 26/07/23.
 */

@Repository
public interface ApplicationAccommodationRepository extends JpaRepository<ApplicationAccommodation, Long> {
    List<ApplicationAccommodation> findAllByGroupIdOrderByVersionDesc(Long groupId);
    List<ApplicationAccommodation> findAllByGroupIdAndStatus(Long group_id, ApplicationStatus status);
}
