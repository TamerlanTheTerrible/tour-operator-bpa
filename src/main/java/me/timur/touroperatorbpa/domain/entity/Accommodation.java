package me.timur.touroperatorbpa.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.timur.touroperatorbpa.accommodation.model.AccommodationCreateDto;
import me.timur.touroperatorbpa.model.enums.AccommodationCategory;

/**
 * Created by Temurbek Ismoilov on 25/07/23.
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "accommodation", indexes = {
        @Index(name = "idx_accommodation_name", columnList = "name"),
        @Index(name = "idx_accommodation_location", columnList = "location_id"),
        @Index(name = "idx_accommodation_category", columnList = "category"),
        @Index(name = "idx_accommodation_is_active", columnList = "is_active"),
        @Index(name = "idx_accommodation_user_company_id", columnList = "user_company_id"),
        @Index(name = "idx_accommodation_rating", columnList = "rating")
})
public class Accommodation extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private AccommodationCategory category;

    @Column(name = "details")
    private String details;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "user_company_id", nullable = false)
    private UserCompany userCompany;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "rating_count", nullable = false)
    private Long ratingCount;

    public Accommodation(AccommodationCreateDto createDto, Location location, UserCompany userCompanyId) {
        this.name = createDto.getAccommodationName();
        this.location = location;
        this.category = createDto.getCategory();
        this.details = createDto.getDetails();
        this.userCompany = userCompanyId;
    }
}
