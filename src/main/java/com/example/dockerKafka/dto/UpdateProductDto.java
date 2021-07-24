package com.example.dockerKafka.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateProductDto {
    private Long productId;
    private String name;
    private BigDecimal price;
    private BigDecimal mobilePrice;
    private String channel;

}
