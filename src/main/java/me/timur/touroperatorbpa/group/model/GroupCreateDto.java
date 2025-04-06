package me.timur.touroperatorbpa.group.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import me.timur.touroperatorbpa.util.LocalDateTimeUtil;

import java.time.LocalDateTime;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

@Data
public class GroupCreateDto {
    @JsonProperty("number")
    private String number;

    @JsonProperty("country")
    private String country;

    @JsonProperty("company_id")
    private Long companyId;

    @Size(min = 1, message = "Size must be greater than 0")
    @JsonProperty(value = "size", required = true)
    private Integer size;

    @JsonProperty("tour_leader_count")
    private Integer tourLeaderCount = 0;

    @NotNull(message = "Arrival time cannot be null")
    @JsonProperty(value = "arrival", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LocalDateTimeUtil.DATE_TIME_PATTERN)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime arrival;

    @NotNull(message = "Departure time cannot be null")
    @JsonProperty(value = "departure", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LocalDateTimeUtil.DATE_TIME_PATTERN)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime departure;

    @JsonProperty("comment")
    private String comment;
}
