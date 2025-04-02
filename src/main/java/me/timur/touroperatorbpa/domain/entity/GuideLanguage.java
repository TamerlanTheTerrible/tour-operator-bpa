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
@Table(name = "guide_language",
        indexes = {@Index(name = "idx_guide_language_guide_id", columnList = "guide_id"), @Index(name = "idx_guide_language_language", columnList = "language")}
)
public class GuideLanguage extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "guide_id", nullable = false)
    private Guide guide;

    @Enumerated(EnumType.STRING)
    @Column(name = "language", nullable = false)
    private Language languages;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;
}
