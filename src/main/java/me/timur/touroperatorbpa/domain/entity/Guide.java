package me.timur.touroperatorbpa.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.timur.touroperatorbpa.model.enums.Language;

import java.util.List;


/**
 * Created by Temurbek Ismoilov on 26/07/23.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "guide",
        uniqueConstraints = @UniqueConstraint(columnNames = {"phone"})
)
public class Guide extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ElementCollection(targetClass = Enum.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "guide_language", joinColumns = @JoinColumn(name = "guide_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "language", nullable = false)
    private List<Language> languages;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "comments")
    private String comments;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "rating_count", nullable = false)
    private Long ratingCount;
}
