package com.example.e_commerce_rest_api.service;

import com.example.e_commerce_rest_api.dto.request.UpdatePaymentStatusRequest;
import com.example.e_commerce_rest_api.dto.response.PaymentDto;

public interface PaymentService {
    PaymentDto updatePaymentStatus (UpdatePaymentStatusRequest request);
}

