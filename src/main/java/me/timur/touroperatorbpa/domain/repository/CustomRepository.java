package me.timur.touroperatorbpa.domain.repository;

import me.timur.touroperatorbpa.domain.entity.BaseEntity;
import me.timur.touroperatorbpa.domain.entity.Group;
import me.timur.touroperatorbpa.model.BaseFilter;
import me.timur.touroperatorbpa.model.group.GroupFilter;
import org.springframework.data.util.Pair;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 29/07/23.
 */

public interface CustomRepository<T extends BaseEntity, R extends BaseFilter> {
     Pair<List<T>, Long> findAllFiltered(R filter);
}
