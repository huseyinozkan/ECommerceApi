package com.example.e_commerce_rest_api.dto.request;

import com.example.e_commerce_rest_api.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderStatusRequest {
    @NotNull
    private Long id;
    @NotNull
    private OrderStatus orderStatus;
}
