package com.example.e_commerce_rest_api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.example.e_commerce_rest_api.enums.AgreementType;
import com.example.e_commerce_rest_api.dto.response.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgreementDto extends BaseDto {
    private Integer version;
    private String cultureName;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private AgreementType type;
}
