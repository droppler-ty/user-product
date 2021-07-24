package com.droppler.core.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto {
    private String name;
    private BigDecimal price;
    private BigDecimal mobilePrice;
}
