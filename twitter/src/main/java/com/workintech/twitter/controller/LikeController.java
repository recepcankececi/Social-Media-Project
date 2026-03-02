package com.workintech.twitter.controller;

import com.workintech.twitter.dto.request.LikeRequestDto;
import com.workintech.twitter.dto.response.LikeResponseDto;
import com.workintech.twitter.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping("/like")
    @ResponseStatus(HttpStatus.CREATED)
    public LikeResponseDto create(@RequestBody LikeRequestDto likeRequestDto) {
        return likeService.create(likeRequestDto);
    }

    @PostMapping("/dislike")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@RequestBody LikeRequestDto likeRequestDto) {
        likeService.deleteById(likeRequestDto.userId());
    }
}
