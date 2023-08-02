package me.timur.touroperatorbpa.application.model.flight;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.timur.touroperatorbpa.domain.enums.ApplicationStatus;
import me.timur.touroperatorbpa.domain.enums.FlightClass;
import me.timur.touroperatorbpa.application.model.AbstractApplication;
import me.timur.touroperatorbpa.util.LocalDateTimeUtil;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class FlightApplicationDto extends AbstractApplication {

    @JsonProperty("items")
    private List<FlightItem> items;

    @Data
    public static class FlightItem {
        private Long id;

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

        @JsonProperty("provided")
        private Integer provided;

        @JsonProperty("comment")
        private String comment;

        @JsonProperty("status")
        public ApplicationStatus status;

        @Override
        public String toString() {
            return "FlightItem{" +
                    "id=" + id +
                    ", date=" + date +
                    ", from='" + from + '\'' +
                    ", to='" + to + '\'' +
                    ", ticketClass=" + ticketClass +
                    ", time='" + time + '\'' +
                    ", requested=" + requested +
                    ", provided=" + provided +
                    ", comment='" + comment + '\'' +
                    ", status=" + status +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "FlightApplicationDto{" +
                "items=" + items +
                ", groupId=" + groupId +
                ", groupNumber='" + groupNumber + '\'' +
                '}';
    }
}
