package com.example.e_commerce_rest_api.dto.request;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumberVerificationRequest {
    @NotEmpty
    @Size(max = 5)
    private String telCode;
    @NotEmpty
    @Size(max = 20)
    private String tel;
    @NotEmpty
    @Size(max = 6, min = 6)
    private String securityCode;
}
