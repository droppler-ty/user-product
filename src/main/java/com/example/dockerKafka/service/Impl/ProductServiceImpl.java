package com.example.dockerKafka.service.Impl;

import com.example.dockerKafka.dto.ChangeProductPriceDto;
import com.example.dockerKafka.dto.ProductDto;
import com.example.dockerKafka.model.Product;
import com.example.dockerKafka.repository.ProductRepository;
import com.example.dockerKafka.service.ProductService;
import com.example.dockerKafka.service.UserFollowProductService;
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
    public String updateProduct(Product updateProduct) {
        Product product = productRepository.findById(updateProduct.getId()).orElseThrow(EntityNotFoundException::new);
        if(!product.getPrice().equals(updateProduct.getPrice())) {
            ChangeProductPriceDto changeProductPriceDto = new ChangeProductPriceDto();
            changeProductPriceDto.setProductId(updateProduct.getId());
            changeProductPriceDto.setOldPrice(product.getPrice());
            changeProductPriceDto.setNewPrice(updateProduct.getPrice());
            changeProductPriceDto.setUserList(userFollowProductService.getUsersWhoFavorited(product.getId()));
            kafkaTemplate.send(TOPIC,changeProductPriceDto);
            product.setPrice(updateProduct.getPrice());
        }
        product.setName(updateProduct.getName());
        productRepository.save(product);
        return "product updated";
    }

}