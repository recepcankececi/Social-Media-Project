package com.workintech.twitter.service;

import com.workintech.twitter.dto.request.LikeRequestDto;
import com.workintech.twitter.dto.response.LikeResponseDto;

public interface LikeService {
    LikeResponseDto create(LikeRequestDto likeRequestDto);
    void deleteById(Long id);
}
