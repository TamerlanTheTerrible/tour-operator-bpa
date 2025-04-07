package me.timur.touroperatorbpa.application.service.impl;

import me.timur.touroperatorbpa.application.service.ApplicationService;
import me.timur.touroperatorbpa.application.service.ApplicationServiceFactory;
import me.timur.touroperatorbpa.model.enums.ApplicationType;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;

/**
 * Created by Temurbek Ismoilov on 07/04/25.
 */

@Component
public class ApplicationServiceFactoryImpl implements ApplicationServiceFactory {

    private final EnumMap<ApplicationType, ApplicationService> applicationServiceMap = new EnumMap<>(ApplicationType.class);

    public ApplicationServiceFactoryImpl(List<ApplicationService> applicationServiceList) {
        applicationServiceList.forEach(applicationService -> applicationServiceMap.put(applicationService.getType(), applicationService));
    }

    @Override
    public ApplicationService getByType(ApplicationType applicationType) {
        return applicationServiceMap.get(applicationType);
    }
}
