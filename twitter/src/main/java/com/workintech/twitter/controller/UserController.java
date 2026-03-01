package com.workintech.twitter.controller;

import com.workintech.twitter.dto.request.UserPatchRequestDto;
import com.workintech.twitter.dto.request.UserRequestDto;
import com.workintech.twitter.dto.response.UserResponseDto;
import com.workintech.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserResponseDto findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto create(@RequestBody UserRequestDto userRequestDto) {
        return userService.create(userRequestDto);
    }

    @PatchMapping("/{id}")
    public UserResponseDto update(@PathVariable("id") Long id, @RequestBody UserPatchRequestDto userPatchRequestDto) {
        return userService.update(id, userPatchRequestDto);
    }

    @PutMapping("/{id}")
    public UserResponseDto replaceOrCreate(@PathVariable("id") Long id, @RequestBody UserRequestDto userRequestDto) {
        return userService.replaceOrCreate(id, userRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }

}
