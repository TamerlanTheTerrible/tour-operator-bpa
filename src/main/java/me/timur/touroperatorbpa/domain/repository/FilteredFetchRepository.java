package me.timur.touroperatorbpa.domain.repository;

import me.timur.touroperatorbpa.domain.entity.BaseEntity;
import me.timur.touroperatorbpa.model.PageableFilter;
import org.springframework.data.util.Pair;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 29/07/23.
 */

public interface FilteredFetchRepository<T extends BaseEntity, R extends PageableFilter> {
     Pair<List<T>, Long> findAllFiltered(R filter);
}
