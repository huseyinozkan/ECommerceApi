package com.example.e_commerce_rest_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocalizationDto {
    private CultureDto culture;
    private List<ResourceDto> resources;
}
