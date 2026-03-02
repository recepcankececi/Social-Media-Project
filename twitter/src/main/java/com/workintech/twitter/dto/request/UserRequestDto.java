package com.workintech.twitter.dto.request;

import java.util.List;

public record UserRequestDto(String username, String email, String password, List<String> roles) {
}
