package com.workintech.twitter.dto.response;

import com.workintech.twitter.entity.Role;

import java.util.List;

public record UserResponseDto(String name, String email, List<Role> roles) {
}
