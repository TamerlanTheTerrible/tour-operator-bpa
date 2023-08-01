package me.timur.touroperatorbpa.model.application.flight;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.timur.touroperatorbpa.domain.enums.FlightClass;
import me.timur.touroperatorbpa.model.application.AbstractApplicationCreate;
import me.timur.touroperatorbpa.util.LocalDateTimeUtil;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class FlightApplicationCreateDto extends AbstractApplicationCreate {

    @JsonProperty("items")
    private List<FlightItem> items;

    @Data
    public static class FlightItem {
        @JsonProperty("date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LocalDateTimeUtil.DATE_PATTERN)
        @JsonDeserialize(using = LocalDateDeserializer.class)
        private LocalDate date;

        @JsonProperty("from")
        private String from;

        @JsonProperty("to")
        private String to;

        @JsonProperty("ticket_class")
        private FlightClass ticketClass;

        @JsonProperty("time")
        private String time;

        @JsonProperty("requested")
        private Integer requested;

        @JsonProperty("comment")
        private String comment;

        @Override
        public String toString() {
            return "FlightItem{" +
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
        return "FlightApplicationCreateDto{" +
                "items=" + items +
                ", groupId=" + groupId +
                '}';
    }
}
