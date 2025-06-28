package com.example.e_commerce_rest_api.repository;

import com.example.e_commerce_rest_api.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByUserIdAndOrderNull(Long userId);
    void deleteAllByUserId(Long userId);

}
