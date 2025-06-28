package com.example.e_commerce_rest_api.service;

import com.example.e_commerce_rest_api.model.request.*;
import com.example.e_commerce_rest_api.model.response.LoginResponse;
import com.example.e_commerce_rest_api.model.response.UserDto;

public interface IAuthService {
    LoginResponse login(LoginRequest request);
    LoginResponse refreshToken(RefreshTokenRequest request);
    UserDto register(RegisterRequest request);
    void sendOtpCode(SendOtpCodeRequest request);
    void forgotPassword(ForgotPasswordRequest request);
    void updatePassword(UpdatePasswordRequest request);
    void approveAgreements(ApproveAgreementRequest request);
}

