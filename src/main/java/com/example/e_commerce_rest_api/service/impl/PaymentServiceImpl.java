package com.example.e_commerce_rest_api.service.impl;

import com.example.e_commerce_rest_api.entity.Payment;
import com.example.e_commerce_rest_api.enums.MessageKey;
import com.example.e_commerce_rest_api.enums.PaymentStatus;
import com.example.e_commerce_rest_api.dto.request.UpdatePaymentStatusRequest;
import com.example.e_commerce_rest_api.dto.response.PaymentDto;
import com.example.e_commerce_rest_api.repository.PaymentRepository;
import com.example.e_commerce_rest_api.service.PaymentService;
import com.example.e_commerce_rest_api.utils.exception.exceptions.NotFoundException;
import com.example.e_commerce_rest_api.utils.helper.MessageSourceHelper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final ModelMapper modelMapper;
    private final MessageSourceHelper messageSourceHelper;
    private final PaymentRepository paymentRepository;

    @Override
    public PaymentDto updatePaymentStatus(UpdatePaymentStatusRequest request) {
        Payment payment = getPaymentById(request.getId());
        payment.setStatus(request.getPaymentStatus());
        if (request.getPaymentStatus() == PaymentStatus.PAID) payment.setPaidAt(LocalDateTime.now());
        return modelMapper.map(paymentRepository.save(payment), PaymentDto.class);
    }

    private Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageSourceHelper.getMessage(MessageKey.PAYMENT_NOT_FOUND)));
    }
}