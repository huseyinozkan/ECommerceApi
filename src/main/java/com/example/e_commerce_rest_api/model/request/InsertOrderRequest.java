package com.example.e_commerce_rest_api.model.request;

import com.example.e_commerce_rest_api.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsertOrderRequest {
    @NotNull
    private PaymentMethod paymentMethod;
    @NotNull
    private Long addressId;
    @NotNull
    private String orderNote;
}
