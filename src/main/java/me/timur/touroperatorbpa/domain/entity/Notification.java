package me.timur.touroperatorbpa.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.timur.touroperatorbpa.model.enums.ApplicationType;
import me.timur.touroperatorbpa.notification.model.NotificationCreateDto;

/**
 * Created by Temurbek Ismoilov on 14/08/23.
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "notification",
        indexes = {@Index(name = "idx_notification_user_id", columnList = "user_id")}
)
public class Notification extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @Enumerated(EnumType.STRING)
    @Column(name = "application_type", nullable = false)
    private ApplicationType applicationType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "is_read", nullable = false)
    private Boolean isRead = false;

    public Notification(NotificationCreateDto createDto, Group group, User user) {
        // TODO
//        this.group = group;
//        this.applicationType = createDto.getA();
//        this.user = user;
//        this.message = createDto.getChange();
//        this.isRead = false;
    }
}
