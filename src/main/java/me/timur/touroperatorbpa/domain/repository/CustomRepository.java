package me.timur.touroperatorbpa.domain.repository;

import me.timur.touroperatorbpa.model.BaseFilter;
import org.springframework.data.util.Pair;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 29/07/23.
 */

public interface CustomRepository<T> {
    <R extends BaseFilter> Pair<List<T>, Long> findAllFiltered(R filter);
}
