package me.timur.touroperatorbpa.accommodation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.timur.touroperatorbpa.accommodation.model.AccommodationCreateDto;
import me.timur.touroperatorbpa.accommodation.model.AccommodationDto;
import me.timur.touroperatorbpa.accommodation.model.AccommodationFilter;
import me.timur.touroperatorbpa.accommodation.service.AccommodationService;
import me.timur.touroperatorbpa.model.enums.Role;
import me.timur.touroperatorbpa.model.response.BaseResponse;
import me.timur.touroperatorbpa.security.user_detatails.UserDetailsImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 03/08/23.
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/accommodation")
public class AccommodationController {

    private final AccommodationService accommodationService;

    @PostMapping(value = {"/create"})
//    @PreAuthorize("hasAnyAuthority(" +
//            "T(me.timur.touroperatorbpa.model.enums.Role).ACCOMMODATION_MANAGER.name(), " +
//            "T(me.timur.touroperatorbpa.model.enums.Role).TOUR_MANAGER.name())")
    public BaseResponse<AccommodationDto> create(@RequestBody @Valid AccommodationCreateDto createDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return BaseResponse.ok(accommodationService.create(createDto, userDetails));
    }

    @GetMapping("/{id}")
    public BaseResponse<AccommodationDto> get(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return BaseResponse.ok(accommodationService.get(id, userDetails));
    }

    @PutMapping(value = {"", "/"})
//    @PreAuthorize("hasAnyAuthority(" +
//            "T(me.timur.touroperatorbpa.model.enums.Role).ACCOMMODATION_MANAGER.name(), " +
//            "T(me.timur.touroperatorbpa.model.enums.Role).TOUR_MANAGER.name())")
    public BaseResponse<AccommodationDto> update(@RequestBody AccommodationDto updateDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return BaseResponse.ok(accommodationService.update(updateDto, userDetails));
    }

    @PostMapping(value = {"", "/"})
    public BaseResponse<List<AccommodationDto>> getAll(@RequestBody @Valid AccommodationFilter filter, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return BaseResponse.ok(accommodationService.getAll(filter, userDetails));
    }
}
