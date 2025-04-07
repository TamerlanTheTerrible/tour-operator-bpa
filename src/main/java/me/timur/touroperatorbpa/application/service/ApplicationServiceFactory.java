package me.timur.touroperatorbpa.application.service;

import me.timur.touroperatorbpa.model.enums.ApplicationType;

/**
 * Created by Temurbek Ismoilov on 07/04/25.
 */

public interface ApplicationServiceFactory {
    ApplicationService getByType(ApplicationType applicationType);
}
