package com.droppler.core.service.Impl;

import com.droppler.core.dto.CreateUserDto;
import com.droppler.core.dto.LoginUserDto;
import com.droppler.core.dto.UserDto;
import com.droppler.core.model.User;
import com.droppler.core.repository.UserRepository;
import com.droppler.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String createUser(CreateUserDto createUserDto) {
        User user = new User();
        user.setEmail(createUserDto.getEmail());
        user.setName(createUserDto.getName());
        if(createUserDto.getChannel().equals("M"))
            user.setIsMobile(true);
        userRepository.save(user);
        return "user created";
    }

    @Override
    public String updateUser(User updateUser) {
        User user = userRepository.findById(updateUser.getId()).orElseThrow(EntityNotFoundException::new);
        user.setName(updateUser.getName());
        user.setEmail(updateUser.getEmail());
        userRepository.save(user);
        return "user updated";
    }

    @Override
    public String loginUser(LoginUserDto loginUserDto) {
        User user= userRepository.findByEmail(loginUserDto.getEmail());
        if(loginUserDto.getChannel().equals("M"))
            user.setIsMobile(true);

        userRepository.save(user);
        return "login success";
    }

    @Override
    public UserDto getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setIsMobile(user.getIsMobile());

        return userDto;
    }


}
