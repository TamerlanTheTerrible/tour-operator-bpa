package me.timur.touroperatorbpa.application.service;

import me.timur.touroperatorbpa.domain.entity.User;
import me.timur.touroperatorbpa.model.PageableFilter;
import me.timur.touroperatorbpa.model.PageableList;
import me.timur.touroperatorbpa.application.model.Application;
import me.timur.touroperatorbpa.application.model.ApplicationCreate;
import me.timur.touroperatorbpa.application.model.accommodation.AccommodationApplicationDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

public interface ApplicationService<T extends ApplicationCreate, R extends Application> {
    @Transactional
    R create(T t, User user);

    @Transactional
    List<R> update(R r, User user);

    void changeStatus(Long id);

    void cancelByGroupId(Long groupId);

    R get(Long id, User user);

    List<R> getByGroupId(Long groupId, User user);

    PageableList<AccommodationApplicationDto.AccommodationItem> getAllFiltered(PageableFilter filter);
}
