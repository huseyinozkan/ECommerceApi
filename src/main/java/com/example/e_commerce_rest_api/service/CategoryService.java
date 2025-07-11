package com.example.e_commerce_rest_api.service;

import com.example.e_commerce_rest_api.dto.request.InsertCategoryRequest;
import com.example.e_commerce_rest_api.dto.request.UpdateCategoryRequest;
import com.example.e_commerce_rest_api.dto.response.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll();
    CategoryDto findById(Long id);
    CategoryDto save(InsertCategoryRequest request);
    CategoryDto update (UpdateCategoryRequest request);
    void deleteById(Long id);
}

