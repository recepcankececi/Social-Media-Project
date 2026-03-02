package com.workintech.twitter.dto.response;

import java.util.List;

public record UserResponseDto(String name, String email, List<String> roles) {
}
