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
@Table(name = "accommodation")
public class Accommodation extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "location", nullable = false)
    private Location location;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private AccommodationCategory category;

    @Column(name = "details")
    private String details;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    public Accommodation(AccommodationCreateDto createDto, Location location, Long companyId) {
        this.name = createDto.getAccommodationName();
        this.location = location;
        this.category = createDto.getCategory();
        this.details = createDto.getDetails();
        this.companyId = companyId;
    }
}
