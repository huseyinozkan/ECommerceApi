package com.example.e_commerce_rest_api.service;

import com.example.e_commerce_rest_api.enums.OrderStatus;
import com.example.e_commerce_rest_api.model.request.InsertOrderRequest;
import com.example.e_commerce_rest_api.model.request.UpdateOrderStatusRequest;
import com.example.e_commerce_rest_api.model.response.OrderDto;
import java.util.List;

public interface IOrderService {
    List<OrderDto> findAll();
    List<OrderDto> findAllAdmin();
    List<OrderDto> findAllAdminByOrderStatus(OrderStatus orderStatus);
    OrderDto findById(Long id);
    OrderDto save(InsertOrderRequest request);
    OrderDto updateOrderStatus (UpdateOrderStatusRequest request);
}

