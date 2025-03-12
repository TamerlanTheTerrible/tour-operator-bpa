package me.timur.touroperatorbpa.accommodation.service;

import me.timur.touroperatorbpa.accommodation.model.AccommodationCreateDto;
import me.timur.touroperatorbpa.accommodation.model.AccommodationDto;
import me.timur.touroperatorbpa.accommodation.model.AccommodationFilter;
import me.timur.touroperatorbpa.security.user_detatails.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 03/08/23.
 */

public interface AccommodationService {
    AccommodationDto create(AccommodationCreateDto createDto, UserDetailsImpl userDetails);
    AccommodationDto get(Long id, UserDetailsImpl userDetails);
    AccommodationDto update(AccommodationDto updateDto, UserDetailsImpl userDetails);
    List<AccommodationDto> getAll(AccommodationFilter filter, UserDetailsImpl userDetails);
}
