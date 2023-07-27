package me.timur.touroperatorbpa.operator.service;

import me.timur.touroperatorbpa.model.application.Application;
import me.timur.touroperatorbpa.model.application.ApplicationCreate;

import java.util.Collection;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

public interface ApplicationService<T extends ApplicationCreate, R extends Application> {
    R create(T t);
    R update(R r);
    void cancel(Long id);
    R get(Long id);
    Collection<R> getAllByOperatorId(Long id);
}
