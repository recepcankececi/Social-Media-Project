package com.workintech.twitter.util.mapper;

import com.workintech.twitter.dto.request.LikeRequestDto;
import com.workintech.twitter.dto.response.LikeResponseDto;
import com.workintech.twitter.entity.Like;
import com.workintech.twitter.entity.Tweet;
import com.workintech.twitter.entity.User;
import org.springframework.stereotype.Component;

@Component
public class LikeMapper {

    public LikeResponseDto toResponseDto(Like like) {
        return new LikeResponseDto(
                like.getId(),
                like.getUser().getId(),
                like.getUser().getUsername(),
                like.getTweet().getId()
        );
    }

    public Like toEntity(LikeRequestDto likeRequestDto, User user, Tweet tweet) {
        Like like = new Like();
        like.setUser(user);
        like.setTweet(tweet);
        return like;
    }
}
