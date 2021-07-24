package com.droppler.core.controller;

import com.droppler.core.dto.CreateUserDto;
import com.droppler.core.dto.LoginUserDto;
import com.droppler.core.model.User;
import com.droppler.core.service.UserService;
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
