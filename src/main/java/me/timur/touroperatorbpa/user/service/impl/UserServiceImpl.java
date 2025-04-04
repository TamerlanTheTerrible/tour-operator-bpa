package me.timur.touroperatorbpa.user.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.timur.touroperatorbpa.domain.entity.User;
import me.timur.touroperatorbpa.domain.entity.UserRole;
import me.timur.touroperatorbpa.domain.repository.UserRepository;
import me.timur.touroperatorbpa.domain.repository.UserRoleRepository;
import me.timur.touroperatorbpa.exception.ClientException;
import me.timur.touroperatorbpa.model.enums.ResponseCode;
import me.timur.touroperatorbpa.user.model.UserCreateDto;
import me.timur.touroperatorbpa.user.model.UserDto;
import me.timur.touroperatorbpa.user.service.UserService;
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
    private final UserRoleRepository userRoleRepository;
//    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDto create(UserCreateDto createDto) {
//        log.info("Creating user: {}", createDto);
//
//        var user = userRepository.save(new User(createDto, createDto.getPassword()));
//        var list = createDto.getRoles().stream().map(role -> new UserRole(user.getId(), role)).toList();
//        userRoleRepository.saveAll(list);
//
//        log.info("User created: {}", user);
//        user.setRoles(list);
//        return new UserDto(user);
        return null;
    }

    @Override
    public UserDto get(Long id) {
        return new UserDto(getEntity(id));
    }

    @Override
    public UserDto changeStatus(Long id, boolean isActive) {
        log.info("Changing status of user with id: {} to {}", id, isActive);

        var user = getEntity(id);
        user.setIsActive(isActive);
        userRepository.save(user);

        return new UserDto(user);
    }

    @Override
    public UserDto changePassword(Long id, String password) {
        log.info("Changing password of user with id: {}", id);

        var user = getEntity(id);

//        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);

        return new UserDto(user);
    }

    @Override
    public UserDto update(UserDto userDto) {
        log.info("Updating user: {}", userDto);

        var user = getEntity(userDto.getUserId());
        if (userDto.getFirstName() != null) {
            user.setFirstName(userDto.getFirstName());
        }

        if (userDto.getLastName() != null) {
            user.setLastName(userDto.getLastName());
        }

        if (userDto.getUsername() != null) {
            user.setUsername(userDto.getUsername());
        }

        if (userDto.getPhoneNumber() != null) {
            user.setPhoneNumber(userDto.getPhoneNumber());
        }

//        if (userDto.getRoles() != null && !userDto.getRoles().isEmpty()) {
//            user.setRoles(userRoleRepository.findAllByNameIn(userDto.getRoles()));
//        }

        userRepository.save(user);
        return new UserDto(user);
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(UserDto::new).toList();
    }

    private User getEntity(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ClientException(ResponseCode.RESOURCE_NOT_FOUND, "User not found"));
    }
}
