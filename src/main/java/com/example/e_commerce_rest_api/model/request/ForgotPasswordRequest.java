package com.example.e_commerce_rest_api.model.request;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordRequest {
    @NotEmpty
    @Size(max = 5)
    private String phoneCode;
    @NotEmpty
    @Size(max = 20)
    private String phoneNumber;
    @NotEmpty
    @Size(max = 6, min = 6)
    private String code;
    @NotEmpty
    @Size(max = 6, min = 6)
    @Pattern(regexp = "\\d{6}")
    private String newPassword;
}
