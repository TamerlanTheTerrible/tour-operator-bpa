package me.timur.touroperatorbpa.accommodation.controller;

import lombok.RequiredArgsConstructor;
import me.timur.touroperatorbpa.accommodation.model.AccommodationCreateDto;
import me.timur.touroperatorbpa.accommodation.model.AccommodationDto;
import me.timur.touroperatorbpa.accommodation.model.AccommodationFilter;
import me.timur.touroperatorbpa.accommodation.service.AccommodationService;
import me.timur.touroperatorbpa.model.response.BaseResponse;
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
    public BaseResponse<AccommodationDto> create(@RequestBody AccommodationCreateDto createDto) {
        //TODO inject company
        return BaseResponse.ok(accommodationService.create(createDto, null));
    }

    @GetMapping("/{id}")
    public BaseResponse<AccommodationDto> get(@PathVariable Long id) {
        return BaseResponse.ok(accommodationService.get(id));
    }

    @PutMapping(value = {"", "/"})
    public BaseResponse<AccommodationDto> update(@RequestBody AccommodationDto updateDto) {
        return BaseResponse.ok(accommodationService.update(updateDto));
    }

    @PostMapping(value = {"", "/"})
    public BaseResponse<List<AccommodationDto>> getAll(@RequestBody AccommodationFilter filter) {
        return BaseResponse.ok(accommodationService.getAll(filter));
    }
}
