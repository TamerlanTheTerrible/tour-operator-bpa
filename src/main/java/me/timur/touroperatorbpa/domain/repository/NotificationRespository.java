package me.timur.touroperatorbpa.domain.repository;

import me.timur.touroperatorbpa.domain.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 31/08/23.
 */

@Repository
public interface NotificationRespository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByGroupAndAndApplicationTypeOrderByIdDesc(Long groupId, String applicationType);
}
