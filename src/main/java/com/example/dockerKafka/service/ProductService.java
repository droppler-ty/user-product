package com.example.dockerKafka.service;

import com.example.dockerKafka.dto.ProductDto;
import com.example.dockerKafka.model.Product;

public interface ProductService {
    String createProduct(ProductDto productDto);

    String updateProduct(Product updateProduct);
}
