package com.droppler.core.service;

import com.droppler.core.dto.UserDto;
import com.droppler.core.dto.UserFollowProductDto;

import java.util.List;

public interface UserFollowProductService {
    String addFavorite(UserFollowProductDto userFollowProductDto);

    List<UserDto> getUsersWhoFavorited(Long productId);

    List<UserDto> getMobileUsersWhoFavorited(Long productId);
}
