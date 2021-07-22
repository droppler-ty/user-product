package com.example.dockerKafka.repository;


import com.example.dockerKafka.model.Product;
import com.example.dockerKafka.model.UserFollowProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFollowProductRepository extends JpaRepository<UserFollowProduct,Long> {
    List<UserFollowProduct> findUserFollowProductsByProductId(Long productId);
}
