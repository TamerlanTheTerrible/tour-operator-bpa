package me.timur.touroperatorbpa.model.application.transport;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.timur.touroperatorbpa.model.application.AbstractApplicationCreate;
import me.timur.touroperatorbpa.util.LocalDateTimeUtil;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class TransportApplicationCreateDto extends AbstractApplicationCreate {

    @JsonProperty("items")
    private List<TransportItem> items;

    @Data
    public static class TransportItem {
        @JsonProperty("date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LocalDateTimeUtil.DATE_PATTERN)
        @JsonDeserialize(using = LocalDateDeserializer.class)
        private LocalDate date;

        @JsonProperty("direction")
        private String direction;

        @JsonProperty("comment")
        public String comment;

        @Override
        public String toString() {
            return "TransportItem{" +
                    "date=" + date +
                    ", direction=" + direction +
                    ", comment='" + comment + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "TransportApplicationCreateDto{" +
                "items=" + items +
                ", groupId=" + groupId +
                '}';
    }
}
