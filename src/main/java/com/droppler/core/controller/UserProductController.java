package com.droppler.core.controller;

import com.droppler.core.dto.UserDto;
import com.droppler.core.dto.UserFollowProductDto;
import com.droppler.core.service.UserFollowProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userFollowProduct")
public class UserProductController {

    private final UserFollowProductService userFollowProductService;

    @Autowired
    public UserProductController(UserFollowProductService userFollowProductService) {
        this.userFollowProductService = userFollowProductService;
    }

    @PostMapping("/")
    public ResponseEntity<String> addFavorite(@RequestBody UserFollowProductDto userFollowProductDto) {
        return ResponseEntity.ok(userFollowProductService.addFavorite(userFollowProductDto));
    }

    @PostMapping("/productId")
    public ResponseEntity<List<UserDto>> getUsers(@RequestBody Long productId) {
        return ResponseEntity.ok(userFollowProductService.getUsersWhoFavorited(productId));
    }
}
