package me.timur.touroperatorbpa.domain.entity.application;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.timur.touroperatorbpa.domain.entity.BaseEntity;
import me.timur.touroperatorbpa.domain.entity.UserCompanyDriver;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "application_transport_driver",
    indexes = {@Index(name = "group_driver_transport_application_id", columnList = "transport_application_id"),
            @Index(name = "group_driver_driver_id", columnList = "user_driver_id")}
)
public class ApplicationTransportDriver extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "transport_application_id", nullable = false)
    private ApplicationTransport application;

    @ManyToOne
    @JoinColumn(name = "user_driver_id", nullable = false)
    private UserCompanyDriver driver;

    @Column(name = "comment")
    private String comment;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "rating_count", nullable = false)
    private Long ratingCount;
}
