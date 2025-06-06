package me.timur.touroperatorbpa.domain.entity.application;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.timur.touroperatorbpa.domain.entity.BaseEntity;
import me.timur.touroperatorbpa.domain.entity.Group;
import me.timur.touroperatorbpa.domain.entity.Restaurant;
import me.timur.touroperatorbpa.model.enums.ApplicationStatus;
import me.timur.touroperatorbpa.model.enums.ApplicationType;
import me.timur.touroperatorbpa.model.enums.MealType;

import java.time.LocalDate;

/**
 * Created by Temurbek Ismoilov on 25/07/23.
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "application_restaurant", indexes = {@Index(columnList = "group_id"), @Index(columnList = "restaurant_id")})
public class ApplicationRestaurant extends BaseEntity implements ApplicationEntity {
    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Enumerated(EnumType.STRING)
    @Column(name = "meal_type", nullable = false)
    private MealType mealType;

    @Column(name = "requested", nullable = false)
    private Integer requested;

    @Column(name = "comment")
    private String comment;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ApplicationStatus status;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "rating_count", nullable = false)
    private Long ratingCount;

    @Override
    public long id() {
        return getId();
    }

    @Override
    public ApplicationType type() {
        return ApplicationType.RESTAURANT;
    }

    @Override
    public ApplicationStatus status() {
        return getStatus();
    }
}
