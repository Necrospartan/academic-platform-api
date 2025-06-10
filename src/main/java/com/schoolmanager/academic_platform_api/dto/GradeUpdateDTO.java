package com.schoolmanager.academic_platform_api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GradeUpdateDTO {
    @NotNull
    @DecimalMin(value = "0.0", message = "Grade must be greater than or equal to 0.0")
    @DecimalMin(value = "20.0", message = "Grade must be less than or equal to 20.0")
    private float value;

    @NotBlank
    @Size(min=2, max=200)
    private String remarks;
}
