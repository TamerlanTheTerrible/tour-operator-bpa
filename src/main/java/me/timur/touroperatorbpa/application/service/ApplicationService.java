package me.timur.touroperatorbpa.application.service;

import me.timur.touroperatorbpa.domain.entity.User;
import me.timur.touroperatorbpa.model.PageableFilter;
import me.timur.touroperatorbpa.model.PageableList;
import me.timur.touroperatorbpa.application.model.ApplicationDto;
import me.timur.touroperatorbpa.application.model.ApplicationCreate;
import me.timur.touroperatorbpa.application.model.accommodation.AccommodationApplicationDto;
import me.timur.touroperatorbpa.model.enums.ApplicationType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

public interface ApplicationService<T extends ApplicationCreate, R extends ApplicationDto> {
    @Transactional
    R create(T t, User user);

    @Transactional
    List<R> update(R r, User user);

    void changeStatus(Long id);

    void cancelByGroupId(Long groupId);

    R get(Long id, User user);

    R getByGroupId(Long groupId, User user);

    ApplicationType getType();

    PageableList<AccommodationApplicationDto.AccommodationItem> getAllFiltered(PageableFilter filter);
}
