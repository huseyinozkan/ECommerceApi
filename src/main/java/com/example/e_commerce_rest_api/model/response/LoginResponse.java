package com.example.e_commerce_rest_api.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
