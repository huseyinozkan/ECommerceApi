package com.example.e_commerce_rest_api.model.response;

import com.example.e_commerce_rest_api.enums.PaymentMethod;
import com.example.e_commerce_rest_api.enums.PaymentStatus;
import com.example.e_commerce_rest_api.model.response.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto extends BaseDto {
    private PaymentMethod paymentMethod;
    private PaymentStatus status;
    private LocalDateTime paidAt;
}
