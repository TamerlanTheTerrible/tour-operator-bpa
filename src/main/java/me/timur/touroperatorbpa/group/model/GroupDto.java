package me.timur.touroperatorbpa.group.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.timur.touroperatorbpa.domain.entity.Group;
import me.timur.touroperatorbpa.domain.entity.application.ApplicationEntity;
import me.timur.touroperatorbpa.model.enums.Country;
import me.timur.touroperatorbpa.model.enums.GroupStatus;
import me.timur.touroperatorbpa.util.LocalDateTimeUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("number")
    private String number;

    @JsonProperty("country")
    private Country country;

    @JsonProperty("company_id")
    private Long companyId;

    @JsonProperty("company_name")
    private String companyName;

    @JsonProperty("size")
    private Integer size;

    @JsonProperty("tour_leader_count")
    private Integer tourLeaderCount;

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

    @JsonProperty("registration_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LocalDateTimeUtil.DATE_TIME_PATTERN)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class) @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime registrationDate;

    @JsonProperty("status")
    private GroupStatus status;

    @JsonProperty("tour_operator_id")
    private Long tourOperatorId;

    @JsonProperty("applications")
    List<GroupApplicationDto> applications = new ArrayList<>();

    public GroupDto(Group group) {
        this.id = group.getId();
        this.number = group.getNumber();
        this.country = group.getCountry();
        this.companyId = group.getPartnerCompany().getId();
        this.companyName = group.getPartnerCompany().getName();
        this.size = group.getSize();
        this.tourLeaderCount = group.getTourLeaderAmount();
        this.arrival = group.getArrivalTime();
        this.departure = group.getDepartureTime();
        this.comment = group.getComment();
        this.tourOperatorId = group.getTourOperator().getId();
        this.registrationDate = group.getDateCreated();
    }

    public GroupDto(Group group, List<ApplicationEntity> applications) {
        this(group);
        this.applications = applications.stream()
                .map(GroupApplicationDto::new)
                .toList();
    }
}
