package com.workintech.twitter.dto.request;

public record UserPatchRequestDto(String name, String email, String password, String role) {
}
