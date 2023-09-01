package me.timur.touroperatorbpa.domain.repository;

import me.timur.touroperatorbpa.domain.entity.ApplicationChangelog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 31/08/23.
 */

@Repository
public interface ApplicationChangeLogRepository extends JpaRepository<ApplicationChangelog, Long> {
    List<ApplicationChangelog> findAllByGroupAndAndApplicationTypeOrderByIdDesc(Long groupId, String applicationType);
}
