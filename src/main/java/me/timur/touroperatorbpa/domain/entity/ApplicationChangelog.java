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
@Table(name = "application_changelog")
public class ApplicationChangelog extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @Enumerated(EnumType.STRING)
    @Column(name = "application_type", nullable = false)
    private ApplicationType applicationType;

    @Column(name = "version", nullable = false)
    private Integer version;

    @Column(name = "change", nullable = false)
    private String change;

    @Column(name = "is_read", nullable = false)
    private Boolean isRead = false;

    public ApplicationChangelog(NotificationCreateDto createDto, Group group) {
        this.group = group;
        this.applicationType = createDto.getApplicationType();
        this.version = createDto.getVersion();
        this.change = createDto.getChange();
        this.isRead = false;
    }
}
