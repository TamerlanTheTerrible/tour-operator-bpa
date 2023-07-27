package me.timur.touroperatorbpa.model.application.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.timur.touroperatorbpa.model.application.AbstractApplicationCreate;
import me.timur.touroperatorbpa.model.application.RoomCreateDto;
import me.timur.touroperatorbpa.util.LocalDateTimeUtil;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class AccommodationApplicationCreateDto extends AbstractApplicationCreate {

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
        private List<RoomCreateDto> rooms;

        @JsonProperty("comment")
        public String comment;


        @Override
        public String toString() {
            return "AccommodationItem{" +
                    "accommodationId=" + accommodationId +
                    ", checkIn=" + checkIn +
                    ", checkOut=" + checkOut +
                    ", rooms=" + rooms +
                    ", comment='" + comment + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AccommodationApplicationCreateDto{" +
                "items=" + items +
                ", groupId=" + groupId +
                '}';
    }
}
