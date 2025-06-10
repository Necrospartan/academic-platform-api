package com.schoolmanager.academic_platform_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.schoolmanager.academic_platform_api.model.AcademicPeriod;
import com.schoolmanager.academic_platform_api.model.Course;
import com.schoolmanager.academic_platform_api.validation.ExistsInDatabase;
import com.schoolmanager.academic_platform_api.validation.UniqueEmail;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentCreatedDTO {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100)
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @UniqueEmail
    private String email;

    @NotBlank(message = "Password is required")
    @JsonProperty(access = Access.WRITE_ONLY)
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&.#^()_+=-])[A-Za-z\\d@$!%*?&.#^()_+=-]{8,}$",
        message = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character"
    )
    private String password;

    @NotNull(message = "Course ID is required")
    @ExistsInDatabase(domainClass = Course.class, fieldName = "id", message = "Course does not exist")
    private Long courseId;
    
    @NotNull(message = "Period ID is required")
    @ExistsInDatabase(domainClass = AcademicPeriod.class, fieldName = "id", message = "Period does not exist")
    private Long periodId;
}
