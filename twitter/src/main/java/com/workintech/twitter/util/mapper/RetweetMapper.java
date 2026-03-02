package com.workintech.twitter.util.mapper;

import com.workintech.twitter.dto.request.RetweetRequestDto;
import com.workintech.twitter.dto.response.RetweetResponseDto;
import com.workintech.twitter.entity.Retweet;
import com.workintech.twitter.entity.Tweet;
import com.workintech.twitter.entity.User;
import org.springframework.stereotype.Component;

@Component
public class RetweetMapper {

    public RetweetResponseDto toResponseDto(Retweet retweet) {
        return new RetweetResponseDto(
                retweet.getId(),
                retweet.getUser().getId(),
                retweet.getUser().getUsername(),
                retweet.getTweet().getId()
        );
    }

    public Retweet toEntity(RetweetRequestDto retweetRequestDto, User user, Tweet tweet) {
        Retweet retweet = new Retweet();
        retweet.setUser(user);
        retweet.setTweet(tweet);
        return retweet;
    }
}
