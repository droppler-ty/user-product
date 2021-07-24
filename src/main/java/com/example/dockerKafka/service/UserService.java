package com.example.dockerKafka.service;

import com.example.dockerKafka.dto.CreateUserDto;
import com.example.dockerKafka.dto.LoginUserDto;
import com.example.dockerKafka.model.User;

public interface UserService {
    String createUser(CreateUserDto createUserDto);
    String updateUser(User user);
    String loginUser(LoginUserDto loginUserDto);
}
