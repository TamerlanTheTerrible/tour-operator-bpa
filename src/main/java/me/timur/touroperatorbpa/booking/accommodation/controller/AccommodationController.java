package me.timur.touroperatorbpa.booking.accommodation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.timur.touroperatorbpa.booking.accommodation.model.AccommodationCreateDto;
import me.timur.touroperatorbpa.booking.accommodation.model.AccommodationDto;
import me.timur.touroperatorbpa.booking.accommodation.service.AccommodationService;
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

    @PostMapping(value = {"", "/"})
    public BaseResponse<AccommodationDto> create(@RequestBody AccommodationCreateDto createDto) {
        return BaseResponse.payload(accommodationService.create(createDto));
    }

    @GetMapping("/{id}")
    public BaseResponse<AccommodationDto> get(@PathVariable Long id) {
        return BaseResponse.payload(accommodationService.get(id));
    }

    @PutMapping(value = {"", "/"})
    public BaseResponse<AccommodationDto> update(@RequestBody AccommodationDto updateDto) {
        return BaseResponse.payload(accommodationService.update(updateDto));
    }

    @GetMapping(value = {"", "/"})
    public BaseResponse<List<AccommodationDto>> getAll() {
        return BaseResponse.payload(accommodationService.getAll());
    }
}
