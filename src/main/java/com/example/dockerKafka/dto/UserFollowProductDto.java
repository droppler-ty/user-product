package com.example.dockerKafka.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFollowProductDto {
    private Long userId;
    private Long productId;
}
