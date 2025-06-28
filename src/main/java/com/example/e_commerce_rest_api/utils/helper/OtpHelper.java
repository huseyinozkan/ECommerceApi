package com.example.e_commerce_rest_api.utils.helper;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class OtpHelper {
    private final SecureRandom random = new SecureRandom();

    public  String generateOtp() {
        int otpLength = 6;
        StringBuilder otp = new StringBuilder(otpLength);

        for (int i = 0; i < otpLength; i++) {
            String digits = "0123456789";
            otp.append(digits.charAt(random.nextInt(digits.length())));
        }

        return otp.toString();
    }
}
