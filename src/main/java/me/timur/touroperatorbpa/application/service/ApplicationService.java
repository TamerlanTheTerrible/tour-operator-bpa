package me.timur.touroperatorbpa.application.service;

import me.timur.touroperatorbpa.model.PageableFilter;
import me.timur.touroperatorbpa.model.PageableList;
import me.timur.touroperatorbpa.application.model.Application;
import me.timur.touroperatorbpa.application.model.ApplicationCreate;
import me.timur.touroperatorbpa.application.model.accommodation.AccommodationApplicationDto;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

public interface ApplicationService<T extends ApplicationCreate, R extends Application> {
    @Transactional
    R create(T t);

    @Transactional
    R update(R r);

    void cancel(Long id);

    void cancelByGroupId(Long groupId);

    R get(Long id);

    R getByGroupId(Long groupId);

    PageableList<AccommodationApplicationDto.AccommodationItem> getAllFiltered(PageableFilter filter);
}
