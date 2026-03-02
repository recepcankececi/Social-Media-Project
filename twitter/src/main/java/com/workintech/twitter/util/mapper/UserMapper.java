package com.workintech.twitter.util.mapper;

import com.workintech.twitter.dto.request.UserPatchRequestDto;
import com.workintech.twitter.dto.request.UserRequestDto;
import com.workintech.twitter.dto.response.UserResponseDto;
import com.workintech.twitter.entity.Role;
import com.workintech.twitter.entity.User;
import com.workintech.twitter.exception.UserException;
import com.workintech.twitter.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    @Autowired
    private RoleRepository roleRepository;

    public UserResponseDto toResponseDto(User user) {
        List<String> roleNames = user.getRoles().stream()
                .map(Role::getName)
                .toList();
        return new UserResponseDto(user.getUsername(), user.getEmail(), roleNames);
    }

    public User toEntity(UserRequestDto userRequestDto){
        User user = new User();
        user.setUsername(userRequestDto.username());
        user.setEmail(userRequestDto.email());
        user.setPassword(userRequestDto.password());
        user.setRoles(convertRoleNamesToRoles(userRequestDto.roles()));
        return user;
    }

    public void UpdateEntity(User user, UserPatchRequestDto userPatchRequestDto){
        user.setUsername(userPatchRequestDto.username());
        user.setEmail(userPatchRequestDto.email());
        user.setPassword(userPatchRequestDto.password());
        user.setRoles(convertRoleNamesToRoles(userPatchRequestDto.roles()));
    }

    private List<Role> convertRoleNamesToRoles(List<String> roleNames) {
        List<Role> roles = new ArrayList<>();
        for (String roleName : roleNames) {
            Role role = roleRepository.findByName(roleName)
                    .orElseThrow(() -> new UserException("Role not found: " + roleName, HttpStatus.BAD_REQUEST));
            roles.add(role);
        }
        return roles;
    }
}
