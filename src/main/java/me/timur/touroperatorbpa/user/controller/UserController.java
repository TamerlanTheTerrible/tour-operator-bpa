package me.timur.touroperatorbpa.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.timur.touroperatorbpa.model.response.BaseResponse;
import me.timur.touroperatorbpa.user.model.PasswordChangeDTO;
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

    @PostMapping(value = {"/create"})
    public BaseResponse<UserDto> create(@Valid @RequestBody UserCreateDto createDto) {
        return BaseResponse.ok(userService.create(createDto));
    }

    @GetMapping("/{id}")
    public BaseResponse<UserDto> get(@PathVariable Long id) {
        return BaseResponse.ok(userService.get(id));
    }

    @PutMapping("/{id}/status/{isActive}")
    public BaseResponse<UserDto> changeStatus(@PathVariable Long id, @PathVariable boolean isActive) {
        return BaseResponse.ok(userService.changeStatus(id, isActive));
    }

    @PutMapping("/change-password")
    public BaseResponse<UserDto> changePassword(@Valid @RequestBody PasswordChangeDTO dto) {
        return BaseResponse.ok(userService.changePassword(dto));
    }

    @GetMapping(value ={"", "/all"})
    public BaseResponse<List<UserDto>> getAll() {
        return BaseResponse.ok(userService.getAll());
    }

    @PutMapping(value = {"/update"})
    public BaseResponse<UserDto> update(@RequestBody UserDto userDto) {
        return BaseResponse.ok(userService.update(userDto));
    }

}
