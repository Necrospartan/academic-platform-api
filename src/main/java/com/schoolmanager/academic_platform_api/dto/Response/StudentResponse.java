package com.schoolmanager.academic_platform_api.dto.Response;

import com.schoolmanager.academic_platform_api.model.Student;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse {
    private Long id;
    private String userName;
    private String userEmail;
    private String roleName;

    public StudentResponse(Student student) {
        this.id = student.getId();
        this.userName = student.getUser().getName();
        this.userEmail = student.getUser().getEmail();
        this.roleName = student.getUser().getRole().getName();
    }
}
