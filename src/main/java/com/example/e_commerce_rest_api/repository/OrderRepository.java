package com.example.e_commerce_rest_api.repository;

import com.example.e_commerce_rest_api.entity.Order;
import com.example.e_commerce_rest_api.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserIdOrderByCreatedDateDesc(Long userId);
    List<Order> findAllByOrderByCreatedDateDesc();
    List<Order> findAllByStatusOrderByCreatedDateDesc(OrderStatus orderStatus);
}
