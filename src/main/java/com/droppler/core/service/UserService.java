package com.droppler.core.service;

import com.droppler.core.dto.CreateUserDto;
import com.droppler.core.dto.LoginUserDto;
import com.droppler.core.dto.UserDto;
import com.droppler.core.model.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    String createUser(CreateUserDto createUserDto);
    String updateUser(User user);
    String loginUser(LoginUserDto loginUserDto);
    UserDto getUser(Long userId);
}
