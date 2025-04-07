package me.timur.touroperatorbpa.domain.entity.application;

import me.timur.touroperatorbpa.model.enums.ApplicationStatus;
import me.timur.touroperatorbpa.model.enums.ApplicationType;

/**
 * Created by Temurbek Ismoilov on 29/08/23.
 */

public interface ApplicationEntity {
    long id();
    ApplicationType type();
    ApplicationStatus status();
}
