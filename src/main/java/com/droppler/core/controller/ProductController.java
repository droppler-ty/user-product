package com.droppler.core.controller;

import com.droppler.core.dto.ProductDto;
import com.droppler.core.dto.UpdateProductDto;
import com.droppler.core.model.Product;
import com.droppler.core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/")
    public ResponseEntity<String>  createProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.createProduct(productDto));
    }
    
    @PutMapping("/")
    public  ResponseEntity<String> updateProduct(@RequestBody UpdateProductDto updateProduct) {
        return ResponseEntity.ok(productService.updateProduct(updateProduct));
    }

    @GetMapping("/")
    public  ResponseEntity<ProductDto> getProduct(@RequestParam Long productId, @RequestParam  Long userId) {
        return ResponseEntity.ok(productService.getProduct(productId,userId));
    }
}
