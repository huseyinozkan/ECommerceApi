package com.example.e_commerce_rest_api.model.request;


import com.example.e_commerce_rest_api.enums.OtpPurpose;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SendOtpCodeRequest {
    @NotEmpty
    @Size(max = 5)
    private String phoneCode;
    @NotEmpty
    @Size(max = 20)
    private String phoneNumber;
    @NotNull
    private OtpPurpose purpose;
}
