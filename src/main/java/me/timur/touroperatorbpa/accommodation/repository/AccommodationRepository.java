package me.timur.touroperatorbpa.accommodation.repository;

import me.timur.touroperatorbpa.domain.entity.Accommodation;
import me.timur.touroperatorbpa.domain.entity.Location;
import me.timur.touroperatorbpa.domain.entity.UserCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 26/07/23.
 */

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
    List<Accommodation> findByLocation(Location location);
}
