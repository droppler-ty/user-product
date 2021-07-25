package com.droppler.core.repository;


import com.droppler.core.model.UserFollowProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFollowProductRepository extends JpaRepository<UserFollowProduct,Long> {
    List<UserFollowProduct> findUserFollowProductsByProductId(Long productId);
}
