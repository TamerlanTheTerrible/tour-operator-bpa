package me.timur.touroperatorbpa.user.service;

import me.timur.touroperatorbpa.user.model.PasswordChangeDTO;
import me.timur.touroperatorbpa.user.model.UserCreateDto;
import me.timur.touroperatorbpa.user.model.UserDto;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 02/08/23.
 */

public interface UserService {

    UserDto create(UserCreateDto userCreateDto);
    UserDto get(Long id);
    UserDto changeStatus(Long id, boolean isActive);
    UserDto changePassword(PasswordChangeDTO dto);
    UserDto update(UserDto userDto);
    List<UserDto> getAll();
}
