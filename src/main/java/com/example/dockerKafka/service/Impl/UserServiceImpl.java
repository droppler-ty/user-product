package com.example.dockerKafka.service.Impl;

import com.example.dockerKafka.dto.CreateUserDto;
import com.example.dockerKafka.model.User;
import com.example.dockerKafka.repository.UserRepository;
import com.example.dockerKafka.service.UserService;
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

}
