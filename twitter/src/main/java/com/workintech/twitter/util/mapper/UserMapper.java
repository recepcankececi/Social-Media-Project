package com.workintech.twitter.util.mapper;

import com.workintech.twitter.dto.request.UserPatchRequestDto;
import com.workintech.twitter.dto.request.UserRequestDto;
import com.workintech.twitter.dto.response.UserResponseDto;
import com.workintech.twitter.entity.User;

public class UserMapper {

    public UserResponseDto toResponseDto(User user) {
        return new UserResponseDto(user.getUsername(), user.getEmail(), user.getRoles());
    }

    public User toEntity(UserRequestDto userRequestDto){

        User user = new User();
        user.setUsername(userRequestDto.name());
        user.setEmail(userRequestDto.email());
        user.setPassword(userRequestDto.password());
        user.setRoles(userRequestDto.roles());
        return user;
    }

    public void UpdateEntity(User user, UserPatchRequestDto userPatchRequestDto){
        user.setUsername(userPatchRequestDto.name());
        user.setEmail(userPatchRequestDto.email());
        user.setPassword(userPatchRequestDto.password());
        user.setRoles(userPatchRequestDto.roles());
    }
}
