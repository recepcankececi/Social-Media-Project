package com.workintech.twitter.service;

import com.workintech.twitter.dto.request.RetweetRequestDto;
import com.workintech.twitter.dto.response.RetweetResponseDto;
import com.workintech.twitter.entity.Retweet;
import com.workintech.twitter.entity.Tweet;
import com.workintech.twitter.entity.User;
import com.workintech.twitter.exception.UserException;
import com.workintech.twitter.exception.UserNotFoundException;
import com.workintech.twitter.repository.RetweetRepository;
import com.workintech.twitter.repository.TweetRepository;
import com.workintech.twitter.repository.UserRepository;
import com.workintech.twitter.util.mapper.RetweetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RetweetServiceImpl implements RetweetService{

    @Autowired
    private RetweetRepository retweetRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private RetweetMapper retweetMapper;

    @Override
    public RetweetResponseDto create(RetweetRequestDto retweetRequestDto) {
        User user = userRepository.findById(retweetRequestDto.userId())
                .orElseThrow(() -> new UserNotFoundException("User not found, id : " + retweetRequestDto.userId()));
        
        Tweet tweet = tweetRepository.findById(retweetRequestDto.tweetId())
                .orElseThrow(() -> new UserException("Tweet not found, id : " + retweetRequestDto.tweetId(), HttpStatus.NOT_FOUND));
        
        Retweet retweet = retweetMapper.toEntity(retweetRequestDto, user, tweet);
        retweetRepository.save(retweet);
        return retweetMapper.toResponseDto(retweet);
    }

    @Override
    public void deleteById(Long id) {
        retweetRepository.deleteById(id);
    }
}
