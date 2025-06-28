package com.example.e_commerce_rest_api.service;

import com.example.e_commerce_rest_api.model.request.UpdatePaymentStatusRequest;
import com.example.e_commerce_rest_api.model.response.PaymentDto;

public interface IPaymentService {
    PaymentDto updatePaymentStatus (UpdatePaymentStatusRequest request);
}

