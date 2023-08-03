package me.timur.touroperatorbpa.application.controller;

import lombok.RequiredArgsConstructor;
import me.timur.touroperatorbpa.application.model.accommodation.AccommodationApplicationCreateDto;
import me.timur.touroperatorbpa.application.model.accommodation.AccommodationApplicationDto;
import me.timur.touroperatorbpa.application.service.ApplicationService;
import me.timur.touroperatorbpa.model.response.BaseResponse;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Temurbek Ismoilov on 03/08/23.
 */

@RequiredArgsConstructor
@RequestMapping("/api/v1/application/accommodation")
@RestController
public class AccommodationApplicationController {

    private final ApplicationService<AccommodationApplicationCreateDto, AccommodationApplicationDto> applicationService;

    @PostMapping(value = {"", "/"})
    public BaseResponse<AccommodationApplicationDto> create(AccommodationApplicationCreateDto accommodationApplicationCreateDto) {
        return BaseResponse.payload(applicationService.create(accommodationApplicationCreateDto));
    }

    @GetMapping("/{id}")
    public BaseResponse<AccommodationApplicationDto> get(@PathVariable Long id) {
        return BaseResponse.payload(applicationService.get(id));
    }
}
