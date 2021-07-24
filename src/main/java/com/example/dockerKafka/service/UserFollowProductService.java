package com.example.dockerKafka.service;

import com.example.dockerKafka.dto.UserDto;
import com.example.dockerKafka.dto.UserFollowProductDto;
import com.example.dockerKafka.model.User;
import com.example.dockerKafka.model.UserFollowProduct;

import java.util.List;

public interface UserFollowProductService {
    String addFavorite(UserFollowProductDto userFollowProductDto);

    List<UserDto> getUsersWhoFavorited(Long productId);

    List<UserDto> getMobileUsersWhoFavorited(Long productId);
}
