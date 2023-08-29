package me.timur.touroperatorbpa.application.model.general;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.timur.touroperatorbpa.model.enums.ApplicationStatus;
import me.timur.touroperatorbpa.application.model.AbstractApplicationDto;
import me.timur.touroperatorbpa.util.LocalDateTimeUtil;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class GeneralApplicationDtoDto extends AbstractApplicationDto {

    @JsonProperty("items")
    private List<Item> items;

    @Data
    public static class Item {
        private Long id;

        @JsonProperty("date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LocalDateTimeUtil.DATE_PATTERN)
        @JsonDeserialize(using = LocalDateDeserializer.class)
        private LocalDate date;

        @JsonProperty("location")
        private String location;

        @JsonProperty("comment")
        private String comment;

        @JsonProperty("status")
        private ApplicationStatus status;

        @Override
        public String toString() {
            return "Item{" +
                    "id=" + id +
                    ", date=" + date +
                    ", location='" + location + '\'' +
                    ", comment='" + comment + '\'' +
                    ", status=" + status +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GeneralApplicationDto{" +
                "items=" + items +
                ", groupId=" + groupId +
                ", groupNumber='" + groupNumber + '\'' +
                '}';
    }
}
