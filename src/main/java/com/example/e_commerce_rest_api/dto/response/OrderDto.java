package com.example.e_commerce_rest_api.dto.response;

import com.example.e_commerce_rest_api.enums.OrderStatus;
import com.example.e_commerce_rest_api.dto.response.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto extends BaseDto {
    private BigDecimal totalAmount;
    private OrderStatus status;
    private PaymentDto payment;
    private AddressDto address;
    private String orderNote;
    private UserDto user;
    private List<CartItemDto> cartItems;
}
