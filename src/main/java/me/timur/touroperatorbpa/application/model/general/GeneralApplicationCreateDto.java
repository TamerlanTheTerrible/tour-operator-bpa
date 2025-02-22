package me.timur.touroperatorbpa.application.model.general;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.timur.touroperatorbpa.application.model.AbstractApplicationCreate;
import me.timur.touroperatorbpa.util.LocalDateTimeUtil;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class GeneralApplicationCreateDto extends AbstractApplicationCreate {

    @JsonProperty("items")
    private List<Item> items;

    @Data
    public static class Item {
        @JsonProperty("date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LocalDateTimeUtil.DATE_PATTERN)
        @JsonDeserialize(using = LocalDateDeserializer.class)
        private LocalDate date;

        @JsonProperty("location")
        private String location;

        @JsonProperty("comment")
        private String comment;

        @Override
        public String toString() {
            return "Item{" +
                    "date=" + date +
                    ", location=" + location +
                    ", comment='" + comment + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GeneralApplicationCreateDto{" +
                "items=" + items +
                ", groupId=" + groupId +
                '}';
    }
}
