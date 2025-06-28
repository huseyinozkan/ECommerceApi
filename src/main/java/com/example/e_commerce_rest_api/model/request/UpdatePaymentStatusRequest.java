package com.example.e_commerce_rest_api.model.request;

import com.example.e_commerce_rest_api.enums.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePaymentStatusRequest {
    @NotNull
    private Long id;
    @NotNull
    private PaymentStatus paymentStatus;
}