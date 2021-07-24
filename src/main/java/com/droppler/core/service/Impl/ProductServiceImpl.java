package com.droppler.core.service.Impl;

import com.droppler.core.dto.ChangeProductPriceDto;
import com.droppler.core.dto.ProductDto;
import com.droppler.core.dto.UpdateProductDto;
import com.droppler.core.model.Product;
import com.droppler.core.repository.ProductRepository;
import com.droppler.core.service.ProductService;
import com.droppler.core.service.UserFollowProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private KafkaTemplate<String, ChangeProductPriceDto> kafkaTemplate;
    private UserFollowProductService userFollowProductService;


    private static final String TOPIC = "ChangeProductPrice";

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              KafkaTemplate<String, ChangeProductPriceDto> kafkaTemplate,
                              UserFollowProductService userFollowProductService) {
        this.productRepository = productRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.userFollowProductService = userFollowProductService;
    }

    @Override
    public String createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        productRepository.save(product);
        return "product created";
    }

    @Override
    public String updateProduct(UpdateProductDto updateProduct) {
        Product product = productRepository.findById(updateProduct.getProductId()).orElseThrow(EntityNotFoundException::new);
        if(!product.getPrice().equals(updateProduct.getPrice())) {
            ChangeProductPriceDto changeProductPriceDto = new ChangeProductPriceDto();
            changeProductPriceDto.setProductId(updateProduct.getProductId());
            changeProductPriceDto.setOldPrice(product.getPrice());
            changeProductPriceDto.setNewPrice(updateProduct.getPrice());
            changeProductPriceDto.setUserList(userFollowProductService.getUsersWhoFavorited(product.getId()));
            kafkaTemplate.send(TOPIC, changeProductPriceDto);
            product.setPrice(updateProduct.getPrice());
        }
        if(!product.getMobilePrice().equals(updateProduct.getMobilePrice())) {
            ChangeProductPriceDto changeProductPriceDto = new ChangeProductPriceDto();
            changeProductPriceDto.setUserList(userFollowProductService.getMobileUsersWhoFavorited(product.getId()));
            changeProductPriceDto.setProductId(updateProduct.getProductId());
            changeProductPriceDto.setOldMobilePrice(product.getPrice());
            changeProductPriceDto.setNewMobilePrice(updateProduct.getPrice());
            kafkaTemplate.send(TOPIC,changeProductPriceDto);
            product.setMobilePrice(updateProduct.getPrice());
        }

        product.setName(updateProduct.getName());
        productRepository.save(product);
        return "product updated";
    }

}
