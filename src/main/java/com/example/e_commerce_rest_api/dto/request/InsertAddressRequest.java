package com.example.e_commerce_rest_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsertAddressRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String city;
    @NotBlank
    private String district;
    private String addressDescription;
}
