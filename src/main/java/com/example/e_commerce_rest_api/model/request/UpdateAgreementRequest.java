package com.example.e_commerce_rest_api.model.request;

import com.example.e_commerce_rest_api.enums.AgreementType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAgreementRequest {
    @NotNull
    Long id;
    @NotNull
    private Integer version;
    @NotNull
    private String cultureName;
    @NotBlank
    private String content;
    @NotNull
    private AgreementType type;
}