package me.timur.touroperatorbpa.model.application.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.timur.touroperatorbpa.domain.enums.TrainClass;
import me.timur.touroperatorbpa.model.application.AbstractApplicationCreate;
import me.timur.touroperatorbpa.util.LocalDateTimeUtil;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class TrainApplicationCreateDto extends AbstractApplicationCreate {

    @JsonProperty("items")
    private List<TrainItem> items;

    @Data
    public static class TrainItem {
        @JsonProperty("date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LocalDateTimeUtil.DATE_PATTERN)
        @JsonDeserialize(using = LocalDateDeserializer.class)
        private LocalDate date;

        @JsonProperty("from")
        private String from;

        @JsonProperty("to")
        private String to;

        @JsonProperty("ticket_class")
        private TrainClass ticketClass;

        @JsonProperty("time")
        private String time;

        @JsonProperty("requested")
        private Integer requested;

        @JsonProperty("comment")
        public String comment;

        @Override
        public String toString() {
            return "TrainItem{" +
                    "date=" + date +
                    ", from='" + from + '\'' +
                    ", to='" + to + '\'' +
                    ", ticketClass=" + ticketClass +
                    ", time='" + time + '\'' +
                    ", requested=" + requested +
                    ", comment='" + comment + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "TrainApplicationCreateDto{" +
                "items=" + items +
                ", groupId=" + groupId +
                '}';
    }
}
