package com.example.e_commerce_rest_api.model.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApproveAgreementRequest {
    @NotEmpty()
    @Valid
    private List<AgreementIdAndType> agreements;
}