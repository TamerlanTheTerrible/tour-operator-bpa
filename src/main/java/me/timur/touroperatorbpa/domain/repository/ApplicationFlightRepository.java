package me.timur.touroperatorbpa.domain.repository;

import me.timur.touroperatorbpa.domain.entity.ApplicationFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Temurbek Ismoilov on 26/07/23.
 */

@Repository
public interface ApplicationFlightRepository extends JpaRepository<ApplicationFlight, Long> {
}
