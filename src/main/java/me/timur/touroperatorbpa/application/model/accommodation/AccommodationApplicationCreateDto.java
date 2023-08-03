package me.timur.touroperatorbpa.application.model.accommodation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.timur.touroperatorbpa.application.model.AbstractApplicationCreate;
import me.timur.touroperatorbpa.util.LocalDateTimeUtil;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class AccommodationApplicationCreateDto extends AbstractApplicationCreate {

    @NotNull @NotEmpty
    @JsonProperty("items")
    private List<AccommodationItem> items;

    @Data
    public static class AccommodationItem {
        @NotNull
        @JsonProperty("accommodation_id")
        private Long accommodationId;

        @NotNull
        @JsonProperty("check_in")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LocalDateTimeUtil.DATE_TIME_PATTERN)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        private LocalDateTime checkIn;

        @NotNull
        @JsonProperty("check_out")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LocalDateTimeUtil.DATE_TIME_PATTERN)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        private LocalDateTime checkOut;

        @NotNull @NotEmpty
        @JsonProperty("rooms")
        private List<RoomDto> rooms;

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
