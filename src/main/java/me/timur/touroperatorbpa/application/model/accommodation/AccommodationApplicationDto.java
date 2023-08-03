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
import me.timur.touroperatorbpa.domain.entity.ApplicationAccommodation;
import me.timur.touroperatorbpa.domain.entity.Group;
import me.timur.touroperatorbpa.model.enums.ApplicationStatus;
import me.timur.touroperatorbpa.application.model.AbstractApplication;
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
public class AccommodationApplicationDto extends AbstractApplication {

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
            this.checkIn = entity.getCheckIn();
            this.checkOut = entity.getCheckOut();
            this.rooms = entity.getRooms().stream().map(RoomDto::new).toList();
            this.comment = entity.getComment();

        }

        @Override
        public String toString() {
            return "AccommodationItem{" +
                    "id=" + id +
                    ", accommodationId=" + accommodationId +
                    ", checkIn=" + checkIn +
                    ", checkOut=" + checkOut +
                    ", rooms=" + rooms +
                    ", comment='" + comment + '\'' +
                    ", status=" + status +
                    '}';
        }
    }

    public AccommodationApplicationDto(Group group, List<ApplicationAccommodation> entities) {
        this.items = entities.stream().map(AccommodationItem::new).toList();
        this.groupId = group.getId();
        this.groupNumber = group.getNumber();
        this.status = this.getOverallStatus(this.items.stream().map(AccommodationItem::getStatus).toList());
    }

    @Override
    public String toString() {
        return "AccommodationApplicationDto{" +
                "items=" + items +
                ", groupId=" + groupId +
                ", groupNumber='" + groupNumber + '\'' +
                '}';
    }
}
