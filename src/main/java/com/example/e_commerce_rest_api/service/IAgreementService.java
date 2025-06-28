package com.example.e_commerce_rest_api.service;

import com.example.e_commerce_rest_api.model.request.InsertAgreementRequest;
import com.example.e_commerce_rest_api.model.request.UpdateAgreementRequest;
import com.example.e_commerce_rest_api.model.response.AgreementDto;

import java.util.List;

public interface IAgreementService {
    List<AgreementDto> findAll();
    AgreementDto findById(Long id);
    List<AgreementDto> currentAgreements();
    AgreementDto save(InsertAgreementRequest request);
    AgreementDto update (UpdateAgreementRequest request);
    void deleteById(Long id);
}

