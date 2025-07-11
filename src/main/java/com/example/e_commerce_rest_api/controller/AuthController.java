package com.example.e_commerce_rest_api.controller;

import com.example.e_commerce_rest_api.controller.base.BaseController;
import com.example.e_commerce_rest_api.dto.request.*;
import com.example.e_commerce_rest_api.dto.response.LoginResponse;
import com.example.e_commerce_rest_api.dto.response.UserDto;
import com.example.e_commerce_rest_api.dto.response.base.BaseResponse;
import com.example.e_commerce_rest_api.service.AuthService;
import com.example.e_commerce_rest_api.service.impl.AuthServiceImpl;
import com.example.e_commerce_rest_api.utils.constants.Authorize;
import com.example.e_commerce_rest_api.utils.helper.MessageSourceHelper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ecommerce/api/v1/auth")
public class AuthController extends BaseController {
    private final AuthService authService;

    public AuthController(MessageSourceHelper messageSourceHelper, AuthService authService) {
        super(messageSourceHelper);
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
        return ok(authService.login(request));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<BaseResponse<LoginResponse>> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        return ok(authService.refreshToken(request));
    }

    @PostMapping("/register")
    public ResponseEntity<BaseResponse<UserDto>> register(@Valid @RequestBody RegisterRequest request) {
        return ok(authService.register(request));
    }

    @PostMapping("/send-otp-code")
    public ResponseEntity<BaseResponse<Void>> sendOtpCode(@Valid @RequestBody SendOtpCodeRequest request) {
        authService.sendOtpCode(request);
        return ok(null);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<BaseResponse<Void>> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
        authService.forgotPassword(request);
        return ok(null);
    }

    @PreAuthorize(Authorize.USER_ADMIN)
    @PostMapping("/update-password")
    public ResponseEntity<BaseResponse<Void>> updatePassword(@Valid @RequestBody UpdatePasswordRequest request) {
        authService.updatePassword(request);
        return ok(null);
    }

    @PreAuthorize(Authorize.USER_ADMIN)
    @PostMapping("/approve-agreements")
    public ResponseEntity<BaseResponse<Void>> approveAgreements(@Valid @RequestBody ApproveAgreementRequest request) {
        authService.approveAgreements(request);
        return ok(null);
    }
}