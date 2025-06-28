package com.example.e_commerce_rest_api.service;

import com.example.e_commerce_rest_api.model.request.InsertAddressRequest;
import com.example.e_commerce_rest_api.model.request.UpdateAddressRequest;
import com.example.e_commerce_rest_api.model.response.AddressDto;
import java.util.List;

public interface IAddressService {
    List<AddressDto> findAll();
    AddressDto findById(Long id);
    AddressDto save(InsertAddressRequest request);
    AddressDto update (UpdateAddressRequest request);
    void deleteById(Long id);
}

