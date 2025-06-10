package com.schoolmanager.academic_platform_api.dto;

import com.schoolmanager.academic_platform_api.validation.ExistsProfessorId;
import com.schoolmanager.academic_platform_api.validation.ExistsSubjectId;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaterialCreatedDTO {
    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 100)
    private String title;

    @NotBlank(message = "Description is required")
    @Size(min = 2, max = 200)
    private String description;

    @NotBlank(message = "File URL is required")
    @Size(min = 2, max = 200)
    private String fileUrl;
    
    @ExistsSubjectId
    @NotNull(message = "Subject ID is required")
    private Long subjectId;

    @NotNull(message = "Professor ID is required")
    @ExistsProfessorId
    private Long professorId;

}
