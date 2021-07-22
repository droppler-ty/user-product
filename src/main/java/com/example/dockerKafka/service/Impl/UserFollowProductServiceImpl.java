package com.example.dockerKafka.service.Impl;

import com.example.dockerKafka.dto.UserDto;
import com.example.dockerKafka.dto.UserFollowProductDto;
import com.example.dockerKafka.model.Product;
import com.example.dockerKafka.model.User;
import com.example.dockerKafka.model.UserFollowProduct;
import com.example.dockerKafka.repository.ProductRepository;
import com.example.dockerKafka.repository.UserFollowProductRepository;
import com.example.dockerKafka.repository.UserRepository;
import com.example.dockerKafka.service.UserFollowProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserFollowProductServiceImpl implements UserFollowProductService {

    private UserFollowProductRepository userFollowProductRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;


    @Autowired
    public UserFollowProductServiceImpl(UserFollowProductRepository userFollowProductRepository,
                                        UserRepository userRepository,
                                        ProductRepository productRepository) {
        this.userFollowProductRepository = userFollowProductRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }


    @Override
    public String addFavorite(UserFollowProductDto userFollowProductDto) {
        User user = userRepository.findById(userFollowProductDto.getUserId()).orElse(new User());
        Product product = productRepository.findById(userFollowProductDto.getProductId()).orElse(new Product());
        if(user == null)
            return "user not found";

        if(product == null)
            return "product not found";

        UserFollowProduct userFollowProduct = new UserFollowProduct();
        userFollowProduct.setProductId(userFollowProductDto.getProductId());
        userFollowProduct.setUserId(userFollowProductDto.getUserId());
        userFollowProductRepository.save(userFollowProduct);

        return "The product has been added to favourites.";
    }

    @Override
    public List<UserDto> getUsersWhoFavorited(Long productId) {

        List<UserFollowProduct> userFollowProductList = new ArrayList<>();
        userFollowProductList = userFollowProductRepository.findUserFollowProductsByProductId(productId);
        List<UserDto> userList = new ArrayList<>();
        userFollowProductList.stream().forEach(x -> {
            User user = userRepository.findById(x.getId()).orElse(new User());
            UserDto userDto = new UserDto();
            userDto.setEmail(user.getEmail());
            userDto.setName(user.getName());
            userDto.setId(user.getId());
            userList.add(userDto);
        });

        return userList;
    }
}
