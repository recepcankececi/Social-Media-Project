package com.workintech.twitter.util.mapper;

import com.workintech.twitter.dto.request.CommentPatchRequestDto;
import com.workintech.twitter.dto.request.CommentRequestDto;
import com.workintech.twitter.dto.response.CommentResponseDto;
import com.workintech.twitter.entity.Comment;
import com.workintech.twitter.entity.Tweet;
import com.workintech.twitter.entity.User;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentResponseDto toResponseDto(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getContent(),
                comment.getUser().getId(),
                comment.getUser().getUsername(),
                comment.getTweet().getId()
        );
    }

    public Comment toEntity(CommentRequestDto commentRequestDto, User user, Tweet tweet) {
        Comment comment = new Comment();
        comment.setContent(commentRequestDto.content());
        comment.setUser(user);
        comment.setTweet(tweet);
        return comment;
    }

    public void updateEntity(Comment comment, CommentPatchRequestDto commentPatchRequestDto) {
        comment.setContent(commentPatchRequestDto.content());
    }
}
