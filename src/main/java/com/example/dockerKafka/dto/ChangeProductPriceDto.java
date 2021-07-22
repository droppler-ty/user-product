package com.example.dockerKafka.dto;

import com.example.dockerKafka.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChangeProductPriceDto {
    private Long productId;
    private String oldPrice;
    private String newPrice;
    private List<UserDto> userList;
}
