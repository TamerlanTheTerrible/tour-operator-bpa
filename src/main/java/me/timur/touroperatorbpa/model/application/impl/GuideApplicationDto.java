package me.timur.touroperatorbpa.model.application.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.timur.touroperatorbpa.domain.enums.ApplicationStatus;
import me.timur.touroperatorbpa.model.application.AbstractApplication;
import me.timur.touroperatorbpa.model.application.AbstractApplicationCreate;
import me.timur.touroperatorbpa.util.LocalDateTimeUtil;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class GuideApplicationDto extends AbstractApplication {

    @JsonProperty("items")
    private List<GuideItem> items;

    @Data
    public static class GuideItem {
        @JsonProperty("from")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LocalDateTimeUtil.DATE_PATTERN)
        @JsonDeserialize(using = LocalDateDeserializer.class)
        private LocalDate from;

        @JsonProperty("to")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LocalDateTimeUtil.DATE_PATTERN)
        @JsonDeserialize(using = LocalDateDeserializer.class)
        private LocalDate to;

        @JsonProperty("guide_id")
        private Long guideId;

        @JsonProperty("comment")
        private String comment;

        @JsonProperty("status")
        private ApplicationStatus status;

        @Override
        public String toString() {
            return "GuideItem{" +
                    "from=" + from +
                    ", to=" + to +
                    ", guideId=" + guideId +
                    ", comment='" + comment + '\'' +
                    ", status=" + status +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GuideApplicationDto{" +
                "items=" + items +
                ", id=" + id +
                ", groupId=" + groupId +
                ", groupNumber='" + groupNumber + '\'' +
                '}';
    }
}
