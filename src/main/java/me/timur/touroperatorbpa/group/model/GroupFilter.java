package me.timur.touroperatorbpa.group.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.timur.touroperatorbpa.domain.enums.GroupStatus;
import me.timur.touroperatorbpa.model.PageableFilter;
import me.timur.touroperatorbpa.util.LocalDateTimeUtil;

import java.time.LocalDateTime;

/**
 * Created by Temurbek Ismoilov on 29/07/23.
 */

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupFilter extends PageableFilter {

    @JsonProperty("tour_operator_id")
    private Long tourOperatorId;

    @JsonProperty("company_id")
    private Long companyId;

    @JsonProperty("company")
    private String country;

    @JsonProperty("arrival_from")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LocalDateTimeUtil.DATE_TIME_PATTERN)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class) @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime arrivalFrom = LocalDateTime.now();

    @JsonProperty("arrival_to")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LocalDateTimeUtil.DATE_TIME_PATTERN)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class) @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime arrivalTo;

    @JsonProperty("status")
    private GroupStatus status;

    @Override
    public String toString() {
        return "GroupFilter{" +
                "tourOperatorId=" + tourOperatorId +
                ", companyId=" + companyId +
                ", country='" + country + '\'' +
                ", arrivalFrom=" + arrivalFrom +
                ", arrivalTo=" + arrivalTo +
                ", pageNumber=" + super.pageNumber +
                ", pageSize=" + super.pageSize +
                '}';
    }
}
