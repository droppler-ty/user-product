package com.droppler.core.service.Impl;

import com.droppler.core.service.UserFollowProductService;
import com.droppler.core.dto.UserDto;
import com.droppler.core.dto.UserFollowProductDto;
import com.droppler.core.model.User;
import com.droppler.core.model.UserFollowProduct;
import com.droppler.core.repository.UserFollowProductRepository;
import com.droppler.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserFollowProductServiceImpl implements UserFollowProductService {

    private UserFollowProductRepository userFollowProductRepository;
    private UserRepository userRepository;


    @Autowired
    public UserFollowProductServiceImpl(UserFollowProductRepository userFollowProductRepository,
                                        UserRepository userRepository) {
        this.userFollowProductRepository = userFollowProductRepository;
        this.userRepository = userRepository;
    }


    @Override
    public String addFavorite(UserFollowProductDto userFollowProductDto) {

		UserFollowProduct userFollowProduct = new UserFollowProduct();
        userFollowProduct.setProductId(userFollowProductDto.getProductId());
        userFollowProduct.setUserId(userFollowProductDto.getUserId());
        userFollowProductRepository.save(userFollowProduct);

        return "The product has been added to favourites.";
    }

    @Override
    public List<UserDto> getUsersWhoFavorited(Long productId) {

		List<UserFollowProduct> userFollowProductList = userFollowProductRepository.findUserFollowProductsByProductId(productId);
        List<UserDto> userList = new ArrayList<>();
        userFollowProductList.forEach(x -> {
            User user = userRepository.findById(x.getUserId()).orElse(new User());
            UserDto userDto = new UserDto();
            userDto.setEmail(user.getEmail());
            userDto.setName(user.getName());
            userDto.setId(user.getId());
            userDto.setIsMobile(user.getIsMobile());
            userList.add(userDto);
        });

        return userList;
    }

    @Override
    public List<UserDto> getMobileUsersWhoFavorited(Long productId) {

      List<UserFollowProduct> userFollowProductList = userFollowProductRepository.findUserFollowProductsByProductId(productId);
        List<UserDto> userList = new ArrayList<>();
        userFollowProductList.forEach(x -> {
            User user = userRepository.findById(x.getUserId()).orElse(new User());
            if(user.getId() != null && user.getIsMobile()) {
                UserDto userDto = new UserDto();
                userDto.setEmail(user.getEmail());
                userDto.setName(user.getName());
                userDto.setId(user.getId());
                userDto.setIsMobile(user.getIsMobile());
                userList.add(userDto);
            }
        });

        return userList;
    }
}
