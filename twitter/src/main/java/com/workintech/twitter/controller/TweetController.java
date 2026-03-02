package com.workintech.twitter.controller;

import com.workintech.twitter.dto.request.TweetPatchRequestDto;
import com.workintech.twitter.dto.request.TweetRequestDto;
import com.workintech.twitter.dto.response.TweetResponseDto;
import com.workintech.twitter.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweet")
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TweetResponseDto create(@RequestBody TweetRequestDto tweetRequestDto) {
        return tweetService.create(tweetRequestDto);
    }

    @GetMapping("/findByUserId")
    public List<TweetResponseDto> findByUserId(@RequestParam Long userId) {
        return tweetService.findByUserId(userId);
    }

    @GetMapping("/findById")
    public TweetResponseDto findById(@RequestParam Long id) {
        return tweetService.findById(id);
    }

    @PutMapping("/{id}")
    public TweetResponseDto update(@PathVariable Long id, @RequestBody TweetPatchRequestDto tweetPatchRequestDto) {
        return tweetService.update(id, tweetPatchRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        tweetService.deleteById(id);
    }
}
