package me.timur.touroperatorbpa.booking.accommodation.service;

import me.timur.touroperatorbpa.booking.accommodation.model.AccommodationCreateDto;
import me.timur.touroperatorbpa.booking.accommodation.model.AccommodationDto;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 03/08/23.
 */

public interface AccommodationService {
    AccommodationDto create(AccommodationCreateDto createDto);
    AccommodationDto get(Long id);
    AccommodationDto update(AccommodationDto updateDto);
    List<AccommodationDto> getAll();
}
