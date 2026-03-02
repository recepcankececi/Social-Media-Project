package com.workintech.twitter.dto.response;

public record CommentResponseDto(Long id, String content, Long userId, String username, Long tweetId) {
}
