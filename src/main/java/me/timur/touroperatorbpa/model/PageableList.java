package me.timur.touroperatorbpa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Temurbek Ismoilov on 30/05/23.
 */

//@Getter
public record PageableList<T>(
        @JsonProperty("count") Long count,
        @JsonProperty("result_list") List<T> resultList
) {
//    public PageableList(Long count, List<T> resultList) {
//        this.count = count;
//        this.resultList = resultList;
//    }

    @Override
    public String toString() {
        return "CriteriaSearchResult{" +
                "count=" + count +
                ", resultList=" + Arrays.toString(resultList.toArray()) +
                '}';
    }
}
