package com.schoolmanager.academic_platform_api.dto;

import com.schoolmanager.academic_platform_api.validation.ExistsCourseId;
import com.schoolmanager.academic_platform_api.validation.ExistsProfessorId;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectCreatedDTO {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100)
    private String name;

    @ExistsProfessorId
    private Long professorId;
    @ExistsCourseId
    private Long courseId;
}
