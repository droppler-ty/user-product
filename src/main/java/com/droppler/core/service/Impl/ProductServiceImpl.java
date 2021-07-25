package com.droppler.core.service.Impl;

import com.droppler.core.dto.ChangeProductPriceDto;
import com.droppler.core.dto.ProductDto;
import com.droppler.core.dto.UpdateProductDto;
import com.droppler.core.dto.UserDto;
import com.droppler.core.model.Product;
import com.droppler.core.repository.ProductRepository;
import com.droppler.core.repository.UserRepository;
import com.droppler.core.service.ProductService;
import com.droppler.core.service.UserFollowProductService;
import com.droppler.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private KafkaTemplate<String, ChangeProductPriceDto> kafkaTemplate;
    private UserFollowProductService userFollowProductService;
    private UserService userService;


    private static final String TOPIC = "ChangeProductPrice";

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              KafkaTemplate<String, ChangeProductPriceDto> kafkaTemplate,
                              UserFollowProductService userFollowProductService,
                              UserService userService) {
        this.productRepository = productRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.userFollowProductService = userFollowProductService;
        this.userService = userService;
    }

    @Override
    public String createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setMobilePrice(productDto.getMobilePrice());
        product.setBarcode(productDto.getBarcode());
        productRepository.save(product);
        return "product created";
    }

    @Override
    public String updateProduct(UpdateProductDto updateProduct) {
        Product product = productRepository.findById(updateProduct.getProductId()).orElseThrow(EntityNotFoundException::new);

        ChangeProductPriceDto changeProductPriceDto = new ChangeProductPriceDto();
        changeProductPriceDto.setProductId(updateProduct.getProductId());

        if(!updateProduct.getPrice().equals(BigDecimal.ZERO) && !product.getPrice().equals(updateProduct.getPrice())) {
            changeProductPriceDto.setOldPrice(product.getPrice());
            changeProductPriceDto.setNewPrice(updateProduct.getPrice());
            changeProductPriceDto.setUserList(userFollowProductService.getUsersWhoFavorited(product.getId()));
            kafkaTemplate.send(TOPIC, changeProductPriceDto);
            product.setPrice(updateProduct.getPrice());
        }
        if(!updateProduct.getMobilePrice().equals(BigDecimal.ZERO) && !product.getMobilePrice().equals(updateProduct.getMobilePrice())) {
            changeProductPriceDto.setUserList(userFollowProductService.getMobileUsersWhoFavorited(product.getId()));
            changeProductPriceDto.setOldMobilePrice(product.getMobilePrice());
            changeProductPriceDto.setNewMobilePrice(updateProduct.getMobilePrice());
            kafkaTemplate.send(TOPIC,changeProductPriceDto);
            product.setMobilePrice(updateProduct.getMobilePrice());
        }

        product.setName(updateProduct.getName());
        productRepository.save(product);
        return "product updated";
    }

    @Override
    public ProductDto getProduct(Long productId, Long userId) {
        UserDto userDto = userService.getUser(userId);
        Product product = productRepository.findById(productId).orElseThrow(EntityNotFoundException::new);

        ProductDto productDto = new ProductDto();
        productDto.setBarcode(product.getBarcode());
        productDto.setName(product.getName());

        if(userDto.getIsMobile().booleanValue())
            productDto.setMobilePrice(product.getMobilePrice());
        else
            productDto.setPrice(product.getPrice());

        return productDto;
    }

}
