package com.workintech.twitter.service;

import com.workintech.twitter.dto.request.TweetPatchRequestDto;
import com.workintech.twitter.dto.request.TweetRequestDto;
import com.workintech.twitter.dto.response.TweetResponseDto;
import com.workintech.twitter.entity.Tweet;
import com.workintech.twitter.entity.User;
import com.workintech.twitter.exception.UserException;
import com.workintech.twitter.exception.UserNotFoundException;
import com.workintech.twitter.repository.TweetRepository;
import com.workintech.twitter.repository.UserRepository;
import com.workintech.twitter.util.mapper.TweetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetServiceImpl implements TweetService{

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TweetMapper tweetMapper;

    @Override
    public TweetResponseDto create(TweetRequestDto tweetRequestDto) {
        User user = userRepository.findById(tweetRequestDto.userId())
                .orElseThrow(() -> new UserNotFoundException("User not found, id : " + tweetRequestDto.userId()));
        
        Tweet tweet = tweetMapper.toEntity(tweetRequestDto, user);
        tweetRepository.save(tweet);
        return tweetMapper.toResponseDto(tweet);
    }

    @Override
    public List<TweetResponseDto> findByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found, id : " + userId));
        
        return user.getTweets()
                .stream()
                .map(tweetMapper::toResponseDto)
                .toList();
    }

    @Override
    public TweetResponseDto findById(Long id) {
        Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(() -> new UserException("Tweet not found, id : " + id, HttpStatus.NOT_FOUND));
        
        return tweetMapper.toResponseDto(tweet);
    }

    @Override
    public TweetResponseDto update(Long id, TweetPatchRequestDto tweetPatchRequestDto) {
        Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(() -> new UserException("Tweet not found, id : " + id, HttpStatus.NOT_FOUND));
        
        tweetMapper.updateEntity(tweet, tweetPatchRequestDto);
        tweetRepository.save(tweet);
        return tweetMapper.toResponseDto(tweet);
    }

    @Override
    public void deleteById(Long id) {
        tweetRepository.deleteById(id);
    }
}
