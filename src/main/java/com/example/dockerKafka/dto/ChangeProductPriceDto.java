package com.example.dockerKafka.dto;

import com.example.dockerKafka.model.User;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ChangeProductPriceDto {
    private Long productId;
    private BigDecimal oldPrice;
    private BigDecimal newPrice;
    private BigDecimal oldMobilePrice;
    private BigDecimal newMobilePrice;
    private List<UserDto> userList;
}
