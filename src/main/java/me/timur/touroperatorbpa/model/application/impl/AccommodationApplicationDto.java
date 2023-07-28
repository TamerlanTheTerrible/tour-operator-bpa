package me.timur.touroperatorbpa.model.application.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.timur.touroperatorbpa.domain.enums.ApplicationStatus;
import me.timur.touroperatorbpa.model.application.AbstractApplication;
import me.timur.touroperatorbpa.model.application.RoomDto;
import me.timur.touroperatorbpa.util.LocalDateTimeUtil;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class AccommodationApplicationDto extends AbstractApplication {

    @JsonProperty("items")
    private List<AccommodationItem> items;

    @Data
    public static class AccommodationItem {
        @JsonProperty("accommodation_id")
        private Long accommodationId;

        @JsonProperty("check_in")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LocalDateTimeUtil.DATE_TIME_PATTERN)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        private LocalDateTime checkIn;

        @JsonProperty("check_out")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LocalDateTimeUtil.DATE_TIME_PATTERN)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        private LocalDateTime checkOut;

        @JsonProperty("rooms")
        private List<RoomDto> rooms;

        @JsonProperty("comment")
        public String comment;

        @JsonProperty("status")
        public ApplicationStatus status;

        @Override
        public String toString() {
            return "AccommodationItem{" +
                    "accommodationId=" + accommodationId +
                    ", checkIn=" + checkIn +
                    ", checkOut=" + checkOut +
                    ", rooms=" + rooms +
                    ", comment='" + comment + '\'' +
                    ", status=" + status +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AccommodationApplicationDto{" +
                "items=" + items +
                ", id=" + id +
                ", groupId=" + groupId +
                ", groupNumber='" + groupNumber + '\'' +
                '}';
    }
}
