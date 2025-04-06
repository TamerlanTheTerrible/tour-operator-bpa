package me.timur.touroperatorbpa.application.model.accommodation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
 import me.timur.touroperatorbpa.application.model.AbstractApplicationDto;
import me.timur.touroperatorbpa.domain.entity.Group;
import me.timur.touroperatorbpa.domain.entity.application.ApplicationAccommodation;
import me.timur.touroperatorbpa.model.enums.ApplicationStatus;
import me.timur.touroperatorbpa.util.LocalDateTimeUtil;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccommodationApplicationDto extends AbstractApplicationDto {

    @JsonProperty("items")
    private List<AccommodationItem> items;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AccommodationItem {
        @NotNull
        public Long id;

        @JsonProperty("accommodation_id")
        private Long accommodationId;

        @JsonProperty("accommodation_name")
        private String accommodationName;

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

        public AccommodationItem(ApplicationAccommodation entity) {
            this.id = entity.getId();
            this.accommodationId = entity.getAccommodation().getId();
            this.accommodationName = entity.getAccommodation().getName();
            this.checkIn = entity.getCheckIn();
            this.checkOut = entity.getCheckOut();
            this.rooms = entity.getApplicationAccommodationRooms().stream().map(RoomDto::new).toList();
            this.comment = entity.getComment();

        }

        @Override
        public String toString() {
            return "AccommodationItem{" +
                    "id=" + id +
                    ", accommodationId=" + accommodationId +
                    ", accommodationName=" + accommodationName +
                    ", checkIn=" + checkIn +
                    ", checkOut=" + checkOut +
                    ", rooms=" + rooms +
                    ", comment='" + comment + '\'' +
                    ", status=" + status +
                    '}';
        }
    }

    public AccommodationApplicationDto(Group group, List<ApplicationAccommodation> entities) {
        super(group, entities);
        this.items = entities.stream().map(AccommodationItem::new).toList();
    }

    @Override
    public String toString() {
        return "AccommodationApplicationDto{" +
                "items=" + items +
                ", groupId=" + groupId +
                ", groupNumber='" + groupNumber + '\'' +
                ", version='" + groupNumber + '\'' +
                '}';
    }
}
