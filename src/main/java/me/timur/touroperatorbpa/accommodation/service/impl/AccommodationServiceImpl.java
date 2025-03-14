package me.timur.touroperatorbpa.accommodation.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.timur.touroperatorbpa.accommodation.model.AccommodationCreateDto;
import me.timur.touroperatorbpa.accommodation.model.AccommodationDto;
import me.timur.touroperatorbpa.accommodation.model.AccommodationFilter;
import me.timur.touroperatorbpa.accommodation.service.AccommodationService;
import me.timur.touroperatorbpa.domain.entity.Accommodation;
import me.timur.touroperatorbpa.domain.entity.Location;
import me.timur.touroperatorbpa.accommodation.repository.AccommodationRepository;
import me.timur.touroperatorbpa.domain.repository.LocationRepository;
import me.timur.touroperatorbpa.exception.ClientException;
import me.timur.touroperatorbpa.model.enums.ResponseCode;
import me.timur.touroperatorbpa.security.user_detatails.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 03/08/23.
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final LocationRepository locationRepository;

    @Override
    public AccommodationDto create(AccommodationCreateDto createDto) {
        log.info("Creating accommodation: {}", createDto);

        var accommodation = new Accommodation(
                createDto,
                getLocation(createDto.getLocationName().toUpperCase()),
                null
        );

        accommodationRepository.save(accommodation);

        return new AccommodationDto(accommodation);
    }

    @Override
    public AccommodationDto get(Long id) {
        return new AccommodationDto(getAccommodationEntity(id));
    }

    @Override
    public AccommodationDto update(AccommodationDto updateDto) {
        log.info("Updating accommodation: {}", updateDto);

        var accommodation = getAccommodationEntity(updateDto.getAccommodationId());

        if (updateDto.getAccommodationName() != null) {
            accommodation.setName(updateDto.getAccommodationName());
        }

        if (updateDto.getLocationName() != null) {
            accommodation.setLocation(getLocation(updateDto.getLocationName().toUpperCase()));
        }
        accommodationRepository.save(accommodation);

        return new AccommodationDto(accommodation);
    }

    @Override
    public List<AccommodationDto> getAll(AccommodationFilter filter) {
        var accommodations = filter.getLocation() == null
                ? accommodationRepository.findAll()
                : accommodationRepository.findByLocation(
                        locationRepository.findByName(filter.getLocation()).orElseThrow(() -> new ClientException(ResponseCode.RESOURCE_NOT_FOUND, "Could not find location: " + filter.getLocation()))
        );

        return accommodations.stream()
                .map(AccommodationDto::new)
                .toList();
    }

    private Accommodation getAccommodationEntity(Long id) {
        return accommodationRepository.findById(id)
                .orElseThrow(() -> new ClientException(ResponseCode.RESOURCE_NOT_FOUND, "Could not find accommodation with id: " + id));
    }

    private Location getLocation(String name) {
        return locationRepository.findByName(name)
                .orElseThrow(() -> new ClientException(ResponseCode.RESOURCE_NOT_FOUND, "Could not find location: " + name));
    }
}
