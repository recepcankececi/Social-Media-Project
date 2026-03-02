package com.workintech.twitter.controller;

import com.workintech.twitter.dto.request.RetweetRequestDto;
import com.workintech.twitter.dto.response.RetweetResponseDto;
import com.workintech.twitter.service.RetweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/retweet")
public class RetweetController {

    @Autowired
    private RetweetService retweetService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RetweetResponseDto create(@RequestBody RetweetRequestDto retweetRequestDto) {
        return retweetService.create(retweetRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        retweetService.deleteById(id);
    }
}
