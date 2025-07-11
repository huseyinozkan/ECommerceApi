package com.example.e_commerce_rest_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private UserDto user;
    private Boolean privacyAgreementHasBeenApproved;
    private Boolean userAgreementHasBeenApproved;
}
