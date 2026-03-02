package com.workintech.twitter.dto.request;

import java.util.List;

public record UserPatchRequestDto(String username, String email, String password, List<String> roles) {
}
