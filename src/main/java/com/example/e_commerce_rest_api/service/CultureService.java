package com.example.e_commerce_rest_api.service;

import com.example.e_commerce_rest_api.dto.request.InsertCultureRequest;
import com.example.e_commerce_rest_api.dto.request.UpdateCultureRequest;
import com.example.e_commerce_rest_api.dto.response.CultureDto;
import java.util.List;

public interface CultureService {
    List<CultureDto> findAll();
    CultureDto findById(Long id);
    CultureDto save(InsertCultureRequest request);
    CultureDto update (UpdateCultureRequest request);
    void deleteById(Long id);
}

