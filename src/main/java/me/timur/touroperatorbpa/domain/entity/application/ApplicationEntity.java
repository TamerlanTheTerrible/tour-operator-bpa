package me.timur.touroperatorbpa.domain.entity.application;

import me.timur.touroperatorbpa.model.enums.ApplicationStatus;

/**
 * Created by Temurbek Ismoilov on 29/08/23.
 */

public interface ApplicationEntity {
    int version();
    ApplicationStatus status();
}
