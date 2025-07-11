package com.example.e_commerce_rest_api.service;

import com.example.e_commerce_rest_api.dto.request.InsertAgreementRequest;
import com.example.e_commerce_rest_api.dto.request.UpdateAgreementRequest;
import com.example.e_commerce_rest_api.dto.response.AgreementDto;

import java.util.List;

public interface AgreementService {
    List<AgreementDto> findAll();
    AgreementDto findById(Long id);
    List<AgreementDto> currentAgreements();
    AgreementDto save(InsertAgreementRequest request);
    AgreementDto update (UpdateAgreementRequest request);
    void deleteById(Long id);
}

