package com.example.dockerKafka.controller;

import com.example.dockerKafka.dto.ProductDto;
import com.example.dockerKafka.model.Product;
import com.example.dockerKafka.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/")
    public ResponseEntity<String>  createProduct (@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.createProduct(productDto));
    }
    
    @PutMapping("/")
    public  ResponseEntity<String> updateProduct(@RequestBody Product updateProduct) {
        return ResponseEntity.ok(productService.updateProduct(updateProduct));
    }
    

}
