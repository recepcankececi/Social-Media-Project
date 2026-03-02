package com.workintech.twitter.service;

import com.workintech.twitter.dto.request.CommentPatchRequestDto;
import com.workintech.twitter.dto.request.CommentRequestDto;
import com.workintech.twitter.dto.response.CommentResponseDto;

public interface CommentService {
    CommentResponseDto create(CommentRequestDto commentRequestDto);
    CommentResponseDto update(Long id, CommentPatchRequestDto commentPatchRequestDto);
    void deleteById(Long id);
}
