package com.example.e_commerce_rest_api.service;

import com.example.e_commerce_rest_api.enums.OrderStatus;
import com.example.e_commerce_rest_api.dto.request.InsertOrderRequest;
import com.example.e_commerce_rest_api.dto.request.UpdateOrderStatusRequest;
import com.example.e_commerce_rest_api.dto.response.OrderDto;
import java.util.List;

public interface OrderService {
    List<OrderDto> findAll();
    List<OrderDto> findAllAdmin();
    List<OrderDto> findAllAdminByOrderStatus(OrderStatus orderStatus);
    OrderDto findById(Long id);
    OrderDto save(InsertOrderRequest request);
    OrderDto updateOrderStatus (UpdateOrderStatusRequest request);
}

