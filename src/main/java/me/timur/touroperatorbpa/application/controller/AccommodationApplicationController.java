package me.timur.touroperatorbpa.application.controller;

import lombok.RequiredArgsConstructor;
import me.timur.touroperatorbpa.annotation.AuthorizedUser;
import me.timur.touroperatorbpa.application.model.accommodation.AccommodationApplicationCreateDto;
import me.timur.touroperatorbpa.application.model.accommodation.AccommodationApplicationDto;
import me.timur.touroperatorbpa.application.service.ApplicationService;
import me.timur.touroperatorbpa.domain.entity.User;
import me.timur.touroperatorbpa.model.response.BaseResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 03/08/23.
 */

@RequiredArgsConstructor
@RequestMapping("/api/v1/application/accommodation")
@RestController
public class AccommodationApplicationController {

    private final ApplicationService<AccommodationApplicationCreateDto, AccommodationApplicationDto> applicationService;

    @PostMapping("/create")
    public BaseResponse<AccommodationApplicationDto> create(@RequestBody AccommodationApplicationCreateDto accommodationApplicationCreateDto, @AuthorizedUser User user) {
        return BaseResponse.ok(applicationService.create(accommodationApplicationCreateDto, user));
    }

    @PutMapping("/update")
    public BaseResponse<List<AccommodationApplicationDto>> update(@RequestBody AccommodationApplicationDto accommodationApplicationDto, @AuthorizedUser User user) {
        return BaseResponse.ok(applicationService.update(accommodationApplicationDto, user));
    }

    @GetMapping("/group/{groupId}")
    public BaseResponse<List<AccommodationApplicationDto>> getByGroupId(@PathVariable Long groupId, @AuthorizedUser User user) {
        return BaseResponse.ok(applicationService.getByGroupId(groupId, user));
    }
}
