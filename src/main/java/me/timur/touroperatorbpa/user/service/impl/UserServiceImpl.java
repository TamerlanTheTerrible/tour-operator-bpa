package me.timur.touroperatorbpa.user.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.timur.touroperatorbpa.domain.entity.User;
import me.timur.touroperatorbpa.domain.entity.UserRole;
import me.timur.touroperatorbpa.domain.repository.UserRepository;
import me.timur.touroperatorbpa.domain.repository.UserRoleRepository;
import me.timur.touroperatorbpa.exception.ClientException;
import me.timur.touroperatorbpa.model.enums.ResponseCode;
import me.timur.touroperatorbpa.model.enums.Role;
import me.timur.touroperatorbpa.security.UserContext;
import me.timur.touroperatorbpa.user.model.PasswordChangeDTO;
import me.timur.touroperatorbpa.user.model.UserCreateDto;
import me.timur.touroperatorbpa.user.model.UserDto;
import me.timur.touroperatorbpa.user.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by Temurbek Ismoilov on 02/08/23.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDto create(UserCreateDto createDto) {
        log.info("Creating user: {}", createDto);

        var user = new User(
                createDto,
                passwordEncoder.encode(createDto.getPassword() != null ? createDto.getPassword() : createDto.getUsername())
        );
        userRepository.save(user);

        var list = createDto.getRoles().stream().map(role -> new UserRole(user, role)).toList();
        userRoleRepository.saveAll(list);

        log.info("User created: {}", user);
        user.setRoles(list);
        return new UserDto(user);
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
    public UserDto changePassword(@Valid PasswordChangeDTO dto) {
        var user = UserContext.getUser();
        log.info("Changing password of user with id: {}", user.getId());

        if (!Objects.equals(dto.getPassword(), dto.getRepeatPassword())) {
            throw new ClientException(ResponseCode.PASSWORD_NOT_MATCH, "Passwords do not match");
        }

        user.setPassword(passwordEncoder.encode(dto.getPassword()));
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

        if (userDto.getRoles() != null && !userDto.getRoles().isEmpty()) {
            var currentRoles = userRoleRepository.findAllByUserId(userDto.getUserId());
            Map<Role, UserRole> roleUserRoleMap = currentRoles.stream()
                    .collect(Collectors.toMap(
                            UserRole::getRole,
                            userRole -> userRole
                    ));

            // Deactivate current roles
            currentRoles.forEach(role -> role.setActive(false));

            // Add new roles
            var newRolesNames = userDto.getRoles();
            newRolesNames.forEach(newRolesName -> {
                if (!roleUserRoleMap.containsKey(newRolesName)) {
                    currentRoles.add(new UserRole(user, newRolesName));
                } else {
                    roleUserRoleMap.get(newRolesName).setActive(true);
                }
            });

            userRoleRepository.saveAll(currentRoles);
        }

        userRepository.save(user);
        return new UserDto(user);
    }

    @Override
    public List<UserDto> getAll() {
        var user = UserContext.getUser();
        return userRepository.findAllByUserCompany(user.getUserCompany())
                .stream()
                .map(UserDto::new)
                .toList();
    }

    private User getEntity(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ClientException(ResponseCode.RESOURCE_NOT_FOUND, "Could not find user with id: " + id));
    }
}
