package com.schoolmanager.academic_platform_api.dto.Response;

import com.schoolmanager.academic_platform_api.model.Professor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessorResponse {
    private Long id;
    private String specialty;
    private String userName;
    private String userEmail;
    private String roleName;

    public ProfessorResponse(Professor professor) {
        this.id = professor.getId();
        this.specialty = professor.getSpecialty();
        this.userName = professor.getUser().getName();
        this.userEmail = professor.getUser().getEmail();
        this.roleName = professor.getUser().getRole().getName();
    }
}
