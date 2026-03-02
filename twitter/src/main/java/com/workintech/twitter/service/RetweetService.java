package com.workintech.twitter.service;

import com.workintech.twitter.dto.request.RetweetRequestDto;
import com.workintech.twitter.dto.response.RetweetResponseDto;

public interface RetweetService {
    RetweetResponseDto create(RetweetRequestDto retweetRequestDto);
    void deleteById(Long id);
}
