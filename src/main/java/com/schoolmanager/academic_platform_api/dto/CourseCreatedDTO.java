package com.schoolmanager.academic_platform_api.dto;

import com.schoolmanager.academic_platform_api.validation.ExistsAcademicPeriodId;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseCreatedDTO {
    @NotBlank(message = "Course name is required")
    @Size(min = 2, max = 100)
    private String name;

    @ExistsAcademicPeriodId
    private Long academicPeriodId;
}
