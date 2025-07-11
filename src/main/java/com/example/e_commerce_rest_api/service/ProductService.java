package com.example.e_commerce_rest_api.service;

import com.example.e_commerce_rest_api.dto.request.InsertProductRequest;
import com.example.e_commerce_rest_api.dto.request.UpdateProductRequest;
import com.example.e_commerce_rest_api.dto.response.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAll();
    List<ProductDto> findAllByCategoryId(Long id);
    ProductDto findById(Long id);
    ProductDto save(InsertProductRequest request);
    ProductDto update (UpdateProductRequest request);
    void deleteById(Long id);
}

