package com.example.e_commerce_rest_api.service;

import com.example.e_commerce_rest_api.model.request.InsertCartItemRequest;
import com.example.e_commerce_rest_api.model.response.CartItemDto;
import java.util.List;

public interface ICartItemService {
    List<CartItemDto> findAll();
    CartItemDto save(InsertCartItemRequest request);
    void deleteById(Long id);
    void deleteAll();
}

