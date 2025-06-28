package com.example.e_commerce_rest_api.service;

import com.example.e_commerce_rest_api.model.request.UpsertResourceRequest;
import com.example.e_commerce_rest_api.model.response.LocalizationDto;

public interface IResourceService {
    LocalizationDto findByName(String name);
    void upsert(UpsertResourceRequest request);
}