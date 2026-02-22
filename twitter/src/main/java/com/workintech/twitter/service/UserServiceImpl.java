package com.workintech.twitter.service;

import com.workintech.twitter.dto.request.UserPatchRequestDto;
import com.workintech.twitter.dto.request.UserRequestDto;
import com.workintech.twitter.dto.response.UserResponseDto;
import com.workintech.twitter.entity.User;
import com.workintech.twitter.exception.UserNotFoundException;
import com.workintech.twitter.repository.UserRepository;
import com.workintech.twitter.util.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserResponseDto> getAll() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::toResponseDto)
                .toList();
    }

    @Override
    public UserResponseDto findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            return userMapper.toResponseDto(user);
        }
        throw new UserNotFoundException("User not found, id : " + id);
    }

    @Override
    public UserResponseDto create(UserRequestDto userRequestDto) {
        User user = userMapper.toEntity(userRequestDto);
        userRepository.save(user);
        return userMapper.toResponseDto(user);
    }

    @Override
    public UserResponseDto update(Long id, UserPatchRequestDto userPatchRequestDto) {
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found, id : " + id));

        userMapper.UpdateEntity(userToUpdate, userPatchRequestDto);
        userRepository.save(userToUpdate);
        return userMapper.toResponseDto(userToUpdate);
    }

    @Override
    public UserResponseDto replaceOrCreate(Long id, UserRequestDto userRequestDto) {
        User user = userMapper.toEntity(userRequestDto);
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent()){
            user.setId(id);
            userRepository.save(user);
            return userMapper.toResponseDto(user);
        }
        userRepository.save(user);
        return userMapper.toResponseDto(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
