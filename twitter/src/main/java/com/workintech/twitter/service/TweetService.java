package com.workintech.twitter.service;

import com.workintech.twitter.dto.request.TweetPatchRequestDto;
import com.workintech.twitter.dto.request.TweetRequestDto;
import com.workintech.twitter.dto.response.TweetResponseDto;

import java.util.List;

public interface TweetService {
    TweetResponseDto create(TweetRequestDto tweetRequestDto);
    List<TweetResponseDto> findByUserId(Long userId);
    TweetResponseDto findById(Long id);
    TweetResponseDto update(Long id, TweetPatchRequestDto tweetPatchRequestDto);
    void deleteById(Long id);
}
