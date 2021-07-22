package com.example.dockerKafka.controller;

import com.example.dockerKafka.dto.UserDto;
import com.example.dockerKafka.dto.UserFollowProductDto;
import com.example.dockerKafka.model.User;
import com.example.dockerKafka.model.UserFollowProduct;
import com.example.dockerKafka.service.UserFollowProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userFollowProduct")
public class UserProductController {

    private UserFollowProductService userFollowProductService;

    @Autowired
    public UserProductController(UserFollowProductService userFollowProductService) {
        this.userFollowProductService = userFollowProductService;
    }

    @PostMapping("/")
    public ResponseEntity<String> addFavorite (@RequestBody UserFollowProductDto userFollowProductDto) {
        return ResponseEntity.ok(userFollowProductService.addFavorite(userFollowProductDto));
    }

    @PostMapping("/productId")
    public ResponseEntity<List<UserDto>> getUsers (@RequestBody Long productId) {
        return ResponseEntity.ok(userFollowProductService.getUsersWhoFavorited(productId));
    }
}
