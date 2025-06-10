package com.schoolmanager.academic_platform_api.dto;

import com.schoolmanager.academic_platform_api.model.Course;
import com.schoolmanager.academic_platform_api.model.Professor;
import com.schoolmanager.academic_platform_api.validation.ExistsInDatabase;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectCreatedDTO {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    @ExistsInDatabase(domainClass = Professor.class, fieldName = "id", message = "Professor does not exist")
    private Long professorId;

    @NotNull
    @ExistsInDatabase(domainClass = Course.class, fieldName = "id", message = "Course does not exist")    
    private Long courseId;
}
