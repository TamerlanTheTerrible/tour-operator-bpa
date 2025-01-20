package me.timur.touroperatorbpa.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.timur.touroperatorbpa.model.enums.ApplicationType;
import me.timur.touroperatorbpa.model.enums.RoleName;
import me.timur.touroperatorbpa.notification.model.NotificationCreateDto;

/**
 * Created by Temurbek Ismoilov on 14/08/23.
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "notification")
public class Notification extends BaseEntity {

    @Column(name = "group_id", nullable = false)
    private Long groupId;

    @Enumerated(EnumType.STRING)
    @Column(name = "application_type", nullable = false)
    private ApplicationType applicationType;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private RoleName roleName;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "is_read", nullable = false)
    private Boolean isRead = false;

    public Notification(NotificationCreateDto createDto, RoleName roleName) {
        this.groupId = createDto.getGroupId();
        this.applicationType = createDto.getApplicationType();
        this.roleName = roleName;
        this.message = createDto.getChange();
        this.isRead = false;
    }
}
