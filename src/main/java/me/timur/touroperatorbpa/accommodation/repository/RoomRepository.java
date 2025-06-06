package me.timur.touroperatorbpa.accommodation.repository;

import me.timur.touroperatorbpa.domain.entity.application.ApplicationAccommodationRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Temurbek Ismoilov on 26/07/23.
 */

@Repository
public interface RoomRepository extends JpaRepository<ApplicationAccommodationRoom, Long> {
}
