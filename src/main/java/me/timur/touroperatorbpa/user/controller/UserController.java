package me.timur.touroperatorbpa.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.timur.touroperatorbpa.model.response.BaseResponse;
import me.timur.touroperatorbpa.user.model.PasswordDto;
import me.timur.touroperatorbpa.user.model.UserCreateDto;
import me.timur.touroperatorbpa.user.model.UserDto;
import me.timur.touroperatorbpa.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 02/08/23.
 */

@RequiredArgsConstructor
@RequestMapping("/api/v1/user/")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping(value = {"/", ""})
    public BaseResponse<UserDto> create(@Valid @RequestBody UserCreateDto createDto) {
        return BaseResponse.payload(userService.create(createDto));
    }

    @GetMapping("/{id}")
    public BaseResponse<UserDto> get(@PathVariable Long id) {
        return BaseResponse.payload(userService.get(id));
    }

    @PutMapping("/{id}/status/{isActive}")
    public BaseResponse<UserDto> changeStatus(@PathVariable Long id, @PathVariable boolean isActive) {
        return BaseResponse.payload(userService.changeStatus(id, isActive));
    }

    @PutMapping("/{id}/change-password")
    public BaseResponse<UserDto> changePassword(@PathVariable Long id, @Valid @RequestBody PasswordDto dto) {
        return BaseResponse.payload(userService.changePassword(id, dto.getPassword()));
    }

    @GetMapping(value ={"", "/all"})
    public BaseResponse<List<UserDto>> getAll() {
        return BaseResponse.payload(userService.getAll());
    }

    @PutMapping(value = {"/", ""})
    public BaseResponse<UserDto> update(@RequestBody UserDto userDto) {
        return BaseResponse.payload(userService.update(userDto));
    }

}
