package me.timur.touroperatorbpa.operator.service;

import me.timur.touroperatorbpa.model.BaseFilter;
import me.timur.touroperatorbpa.model.PageableList;
import me.timur.touroperatorbpa.model.application.Application;
import me.timur.touroperatorbpa.model.application.ApplicationCreate;
import me.timur.touroperatorbpa.model.application.accommodation.AccommodationApplicationDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

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

    PageableList<AccommodationApplicationDto.AccommodationItem> getAllFiltered(BaseFilter filter);
}
