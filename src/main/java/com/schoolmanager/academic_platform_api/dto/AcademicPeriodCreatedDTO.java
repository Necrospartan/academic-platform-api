package com.schoolmanager.academic_platform_api.dto;

import java.time.LocalDate;

import com.schoolmanager.academic_platform_api.validation.ValidDateRange;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@ValidDateRange
@Getter
@Setter
public class AcademicPeriodCreatedDTO {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100)
    private String name;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;
    @NotNull(message = "End date is required")
    private LocalDate endDate;
}
