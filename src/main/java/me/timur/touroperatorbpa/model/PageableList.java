package me.timur.touroperatorbpa.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Temurbek Ismoilov on 30/05/23.
 */

@Getter
public class PageableList<T> {
    private final Long count;
    private final List<T> resultList;

    public PageableList(Long count, List<T> resultList) {
        this.count = count;
        this.resultList = resultList;
    }

    @Override
    public String toString() {
        return "CriteriaSearchResult{" +
                "count=" + count +
                ", resultList=" + Arrays.toString(resultList.toArray()) +
                '}';
    }
}
