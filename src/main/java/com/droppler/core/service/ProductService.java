package com.droppler.core.service;

import com.droppler.core.dto.ProductDto;
import com.droppler.core.dto.UpdateProductDto;
import org.springframework.stereotype.Component;

@Component
public interface ProductService {
    String createProduct(ProductDto productDto);

    String updateProduct(UpdateProductDto updateProduct);
}
