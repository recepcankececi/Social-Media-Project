package com.workintech.twitter.dto.request;

import com.workintech.twitter.entity.Role;

import java.util.List;

public record UserRequestDto(String name, String email, String password, List<Role> roles) {
}
