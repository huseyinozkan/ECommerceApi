package com.example.e_commerce_rest_api.service;

import com.example.e_commerce_rest_api.dto.request.UpsertResourceRequest;
import com.example.e_commerce_rest_api.dto.response.LocalizationDto;

public interface ResourceService {
    LocalizationDto findByName(String name);
    void upsert(UpsertResourceRequest request);
}