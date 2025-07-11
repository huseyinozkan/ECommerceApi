package com.example.e_commerce_rest_api.service;

import com.example.e_commerce_rest_api.dto.request.InsertCartItemRequest;
import com.example.e_commerce_rest_api.dto.response.CartItemDto;
import java.util.List;

public interface CartItemService {
    List<CartItemDto> findAll();
    CartItemDto save(InsertCartItemRequest request);
    void deleteById(Long id);
    void deleteAll();
}

