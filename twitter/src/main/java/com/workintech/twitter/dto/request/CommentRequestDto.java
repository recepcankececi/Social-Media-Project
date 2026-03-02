package com.workintech.twitter.dto.request;

public record CommentRequestDto(String content, Long userId, Long tweetId) {
}
