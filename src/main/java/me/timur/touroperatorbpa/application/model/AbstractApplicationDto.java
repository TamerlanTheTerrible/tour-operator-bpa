package me.timur.touroperatorbpa.application.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.timur.touroperatorbpa.domain.entity.Group;
import me.timur.touroperatorbpa.domain.entity.application.ApplicationEntity;
import me.timur.touroperatorbpa.model.enums.ApplicationStatus;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

@Data
public class AbstractApplicationDto implements ApplicationDto {
    @JsonProperty("group_id")
    public Long groupId;

    @JsonProperty("group_number")
    public String groupNumber;

    @JsonProperty("status")
    public ApplicationStatus status;

    @JsonProperty("version")
    public Integer version;

    public ApplicationStatus getOverallStatus(List<ApplicationStatus> statusList) {
        return statusList.stream().allMatch(s -> s == ApplicationStatus.CONFIRMED)
                ? ApplicationStatus.CONFIRMED
                : statusList.stream().allMatch(s -> s == ApplicationStatus.CANCELLED)
                ? ApplicationStatus.CANCELLED
                : statusList.stream().anyMatch(s -> s == ApplicationStatus.DEPRECATED)
                ? ApplicationStatus.DEPRECATED : ApplicationStatus.ACTIVE;
    }

    public static <T extends AbstractApplicationDto, E extends ApplicationEntity> List<T> toList(Group group, List<E> entities, Class<T> dtoClass) {
        var applicationMap = entities.stream().collect(Collectors.groupingBy(ApplicationEntity::version));
        var applicationDtos = new ArrayList<T>();
        int currentVersion = applicationMap.keySet().stream().max(Integer::compareTo).orElse(0);
        for (int i = currentVersion; i > 0; i--) {
            var applications = applicationMap.get(i);
            if (applications != null) {
                try {
                    Constructor<T> constructor = dtoClass.getConstructor(Group.class, List.class);
                    applicationDtos.add(constructor.newInstance(group, applications));
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                    // Handle exception appropriately
                }
            }
        }

        return applicationDtos;
    }

    protected AbstractApplicationDto() {
    }

    protected <E extends ApplicationEntity> AbstractApplicationDto(Group group, List<E> entities) {
        this.groupId = group.getId();
        this.groupNumber = group.getNumber();
        this.status = getOverallStatus(entities.stream().map(ApplicationEntity::status).collect(Collectors.toList()));
        this.version = entities.get(0).version();
    }
}
