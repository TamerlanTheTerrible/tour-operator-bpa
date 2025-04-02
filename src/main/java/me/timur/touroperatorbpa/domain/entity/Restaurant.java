package me.timur.touroperatorbpa.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Temurbek Ismoilov on 26/07/23.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "restaurant",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "location_id"}),
        indexes = @Index(columnList = "location_id")
)
public class Restaurant extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "rating_count", nullable = false)
    private Long ratingCount;

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", location=" + location +
                ", isActive=" + isActive +
                '}';
    }
}
