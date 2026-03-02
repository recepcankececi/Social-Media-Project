package com.workintech.twitter.util.mapper;

import com.workintech.twitter.dto.request.TweetPatchRequestDto;
import com.workintech.twitter.dto.request.TweetRequestDto;
import com.workintech.twitter.dto.response.TweetResponseDto;
import com.workintech.twitter.entity.Tweet;
import com.workintech.twitter.entity.User;
import org.springframework.stereotype.Component;

@Component
public class TweetMapper {

    public TweetResponseDto toResponseDto(Tweet tweet) {
        return new TweetResponseDto(
                tweet.getId(),
                tweet.getContent(),
                tweet.getUser().getId(),
                tweet.getUser().getUsername()
        );
    }

    public Tweet toEntity(TweetRequestDto tweetRequestDto, User user) {
        Tweet tweet = new Tweet();
        tweet.setContent(tweetRequestDto.content());
        tweet.setUser(user);
        return tweet;
    }

    public void updateEntity(Tweet tweet, TweetPatchRequestDto tweetPatchRequestDto) {
        tweet.setContent(tweetPatchRequestDto.content());
    }
}
