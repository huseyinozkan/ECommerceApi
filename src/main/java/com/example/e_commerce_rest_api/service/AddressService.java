package com.example.e_commerce_rest_api.service;

import com.example.e_commerce_rest_api.dto.request.InsertAddressRequest;
import com.example.e_commerce_rest_api.dto.request.UpdateAddressRequest;
import com.example.e_commerce_rest_api.dto.response.AddressDto;
import java.util.List;

public interface AddressService {
    List<AddressDto> findAll();
    AddressDto findById(Long id);
    AddressDto save(InsertAddressRequest request);
    AddressDto update (UpdateAddressRequest request);
    void deleteById(Long id);
}

