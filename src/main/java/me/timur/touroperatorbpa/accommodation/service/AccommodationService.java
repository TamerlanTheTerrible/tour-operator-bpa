package me.timur.touroperatorbpa.accommodation.service;

import me.timur.touroperatorbpa.accommodation.model.AccommodationCreateDto;
import me.timur.touroperatorbpa.accommodation.model.AccommodationDto;
import me.timur.touroperatorbpa.accommodation.model.AccommodationFilter;
import me.timur.touroperatorbpa.domain.entity.Company;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 03/08/23.
 */

public interface AccommodationService {
    AccommodationDto create(AccommodationCreateDto createDto, Company company);
    AccommodationDto get(Long id);
    AccommodationDto update(AccommodationDto updateDto);
    List<AccommodationDto> getAll(AccommodationFilter filter);
}
