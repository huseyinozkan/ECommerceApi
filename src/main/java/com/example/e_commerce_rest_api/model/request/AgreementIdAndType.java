package com.example.e_commerce_rest_api.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.example.e_commerce_rest_api.enums.AgreementType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgreementIdAndType {
    @NotNull
    private Long id;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private AgreementType type;
}