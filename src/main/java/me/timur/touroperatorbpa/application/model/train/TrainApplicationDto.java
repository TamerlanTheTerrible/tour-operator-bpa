package me.timur.touroperatorbpa.application.model.train;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.timur.touroperatorbpa.model.enums.ApplicationStatus;
import me.timur.touroperatorbpa.model.enums.TrainClass;
import me.timur.touroperatorbpa.application.model.AbstractApplication;
import me.timur.touroperatorbpa.util.LocalDateTimeUtil;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class TrainApplicationDto extends AbstractApplication {

    @JsonProperty("items")
    private List<TrainItem> items;

    @Data
    public static class TrainItem {
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
        private TrainClass ticketClass;

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
            return "TrainItem{" +
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
        return "TrainApplicationDto{" +
                "items=" + items +
                ", groupId=" + groupId +
                ", groupNumber='" + groupNumber + '\'' +
                '}';
    }
}
