package me.timur.touroperatorbpa.application.model.restaurant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.timur.touroperatorbpa.model.enums.MealType;
import me.timur.touroperatorbpa.application.model.AbstractApplicationCreate;
import me.timur.touroperatorbpa.util.LocalDateTimeUtil;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class RestaurantApplicationCreateDto extends AbstractApplicationCreate {

    @JsonProperty("items")
    private List<RestaurantItem> restaurantItems;

    @Data
    public static class RestaurantItem {
        @JsonProperty("date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LocalDateTimeUtil.DATE_PATTERN)
        @JsonDeserialize(using = LocalDateDeserializer.class)
        private LocalDate date;

        @JsonProperty("restaurant_id")
        private Long restaurantId;

        @JsonProperty("meal_type")
        private MealType mealType;

        @JsonProperty("requested")
        private Integer requested;

        @Override
        public String toString() {
            return "Item{" +
                    "date=" + date +
                    ", restaurantId=" + restaurantId +
                    ", mealType=" + mealType +
                    ", requested=" + requested +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "RestaurantApplicationCreateDto{" +
                "items=" + restaurantItems +
                ", groupId=" + groupId +
                '}';
    }
}
