package com.schoolmanager.academic_platform_api.dto.Response;

import com.schoolmanager.academic_platform_api.model.Subject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SubjectResponse {
    private Long id;
    private String name;
    private String profesorName;
    private String courseName;

    public SubjectResponse(Subject subject) {
        this.id = subject.getId();
        this.name = subject.getName();
        this.profesorName = subject.getProfessor().getUser().getName();
        this.courseName = subject.getCourse().getName();
    }
}
