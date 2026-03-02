package com.workintech.twitter.controller;

import com.workintech.twitter.dto.request.CommentPatchRequestDto;
import com.workintech.twitter.dto.request.CommentRequestDto;
import com.workintech.twitter.dto.response.CommentResponseDto;
import com.workintech.twitter.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponseDto create(@RequestBody CommentRequestDto commentRequestDto) {
        return commentService.create(commentRequestDto);
    }

    @PutMapping("/{id}")
    public CommentResponseDto update(@PathVariable Long id, @RequestBody CommentPatchRequestDto commentPatchRequestDto) {
        return commentService.update(id, commentPatchRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        commentService.deleteById(id);
    }
}
