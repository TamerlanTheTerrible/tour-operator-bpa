package me.timur.touroperatorbpa.model.group;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import me.timur.touroperatorbpa.domain.entity.Group;
import me.timur.touroperatorbpa.util.LocalDateTimeUtil;

import java.time.LocalDateTime;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

@Data
public class GroupDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("number")
    private String number;

    @JsonProperty("country")
    private String country;

    @JsonProperty("company_id")
    private Long companyId;

    @JsonProperty("company_name")
    private String companyName;

    @JsonProperty("size")
    private Integer size;

    @JsonProperty("tour_leader_amount")
    private Integer tourLeaderAmount;

    @JsonProperty("arrival")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LocalDateTimeUtil.DATE_TIME_PATTERN)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class) @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime arrival;

    @JsonProperty("departure")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LocalDateTimeUtil.DATE_TIME_PATTERN)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class) @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime departure;

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("tour_operator_id")
    private Long tourOperatorId;

    public GroupDto(Group group) {
        this.id = group.getId();
        this.number = group.getNumber();
        this.country = group.getCountry();
        this.companyId = group.getCompany().getId();
        this.companyName = group.getCompany().getName();
        this.size = group.getSize();
        this.tourLeaderAmount = group.getTourLeaderAmount();
        this.arrival = group.getArrival();
        this.departure = group.getDeparture();
        this.comment = group.getComment();
        this.tourOperatorId = group.getTourOperator().getId();
    }
}
