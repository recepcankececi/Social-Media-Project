package com.workintech.twitter.service;

import com.workintech.twitter.dto.request.LikeRequestDto;
import com.workintech.twitter.dto.response.LikeResponseDto;
import com.workintech.twitter.entity.Like;
import com.workintech.twitter.entity.Tweet;
import com.workintech.twitter.entity.User;
import com.workintech.twitter.exception.UserException;
import com.workintech.twitter.exception.UserNotFoundException;
import com.workintech.twitter.repository.LikeRepository;
import com.workintech.twitter.repository.TweetRepository;
import com.workintech.twitter.repository.UserRepository;
import com.workintech.twitter.util.mapper.LikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService{

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private LikeMapper likeMapper;

    @Override
    public LikeResponseDto create(LikeRequestDto likeRequestDto) {
        User user = userRepository.findById(likeRequestDto.userId())
                .orElseThrow(() -> new UserNotFoundException("User not found, id : " + likeRequestDto.userId()));
        
        Tweet tweet = tweetRepository.findById(likeRequestDto.tweetId())
                .orElseThrow(() -> new UserException("Tweet not found, id : " + likeRequestDto.tweetId(), HttpStatus.NOT_FOUND));
        
        Like like = likeMapper.toEntity(likeRequestDto, user, tweet);
        likeRepository.save(like);
        return likeMapper.toResponseDto(like);
    }

    @Override
    public void deleteById(Long id) {
        likeRepository.deleteById(id);
    }
}
