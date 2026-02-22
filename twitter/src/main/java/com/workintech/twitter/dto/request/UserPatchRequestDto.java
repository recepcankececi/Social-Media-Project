package com.workintech.twitter.dto.request;

import com.workintech.twitter.entity.Role;

import java.util.List;

public record UserPatchRequestDto(String name, String email, String password, List<Role> roles) {
}
