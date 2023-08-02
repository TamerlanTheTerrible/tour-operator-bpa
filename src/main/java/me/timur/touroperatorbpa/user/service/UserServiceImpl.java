package me.timur.touroperatorbpa.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.timur.touroperatorbpa.domain.entity.User;
import me.timur.touroperatorbpa.domain.repository.RoleRepository;
import me.timur.touroperatorbpa.domain.repository.UserRepository;
import me.timur.touroperatorbpa.user.model.UserCreateDto;
import me.timur.touroperatorbpa.user.model.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 02/08/23.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDto create(UserCreateDto userCreateDto) {
        log.info("Creating user: {}", userCreateDto);
        User user = new User(userCreateDto);
        return null;
    }

    @Override
    public UserDto get(Long id) {
        return null;
    }

    @Override
    public UserDto deactivate(Long id) {
        return null;
    }

    @Override
    public UserDto activate(Long id) {
        return null;
    }

    @Override
    public UserDto update(UserDto userDto) {
        return null;
    }

    @Override
    public List<UserDto> getAll() {
        return null;
    }
}
