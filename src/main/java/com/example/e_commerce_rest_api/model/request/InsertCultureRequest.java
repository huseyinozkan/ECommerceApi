package com.example.e_commerce_rest_api.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsertCultureRequest {
    @NotBlank()
    @Size(max = 20)
    private String name;

    @NotBlank()
    @Size(max = 50)
    private String title;
}