package com.workintech.twitter.service;

import com.workintech.twitter.dto.request.CommentPatchRequestDto;
import com.workintech.twitter.dto.request.CommentRequestDto;
import com.workintech.twitter.dto.response.CommentResponseDto;
import com.workintech.twitter.entity.Comment;
import com.workintech.twitter.entity.Tweet;
import com.workintech.twitter.entity.User;
import com.workintech.twitter.exception.UserException;
import com.workintech.twitter.exception.UserNotFoundException;
import com.workintech.twitter.repository.CommentRepository;
import com.workintech.twitter.repository.TweetRepository;
import com.workintech.twitter.repository.UserRepository;
import com.workintech.twitter.util.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public CommentResponseDto create(CommentRequestDto commentRequestDto) {
        User user = userRepository.findById(commentRequestDto.userId())
                .orElseThrow(() -> new UserNotFoundException("User not found, id : " + commentRequestDto.userId()));
        
        Tweet tweet = tweetRepository.findById(commentRequestDto.tweetId())
                .orElseThrow(() -> new UserException("Tweet not found, id : " + commentRequestDto.tweetId(), HttpStatus.NOT_FOUND));
        
        Comment comment = commentMapper.toEntity(commentRequestDto, user, tweet);
        commentRepository.save(comment);
        return commentMapper.toResponseDto(comment);
    }

    @Override
    public CommentResponseDto update(Long id, CommentPatchRequestDto commentPatchRequestDto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new UserException("Comment not found, id : " + id, HttpStatus.NOT_FOUND));
        
        commentMapper.updateEntity(comment, commentPatchRequestDto);
        commentRepository.save(comment);
        return commentMapper.toResponseDto(comment);
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}
