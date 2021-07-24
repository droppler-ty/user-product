package com.example.dockerKafka.controller;

import com.example.dockerKafka.dto.CreateUserDto;
import com.example.dockerKafka.dto.LoginUserDto;
import com.example.dockerKafka.model.User;
import com.example.dockerKafka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<String>  createUser (@RequestBody CreateUserDto createUserDto) {
        return ResponseEntity.ok(userService.createUser(createUserDto));
    }
    
    @PutMapping("/")
    public  ResponseEntity<String> updateUser(@RequestBody User updateUser) {
        return ResponseEntity.ok(userService.updateUser(updateUser));
    }

    @PostMapping("/login")
    public ResponseEntity<String>  createUser (@RequestBody LoginUserDto loginUserDto) {
        return ResponseEntity.ok(userService.loginUser(loginUserDto));
    }
    

}
