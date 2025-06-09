package com.schoolmanager.academic_platform_api.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

import com.schoolmanager.academic_platform_api.model.Course;
import com.schoolmanager.academic_platform_api.model.Subject;

@Getter
@Setter
public class CourseResponse {
    private Long id;
    private String name;

    //Academic Period
    private String academicPeriod;

    //Subjects
    private List<String> subjects;
    public CourseResponse(Course course) {
        this.id = course.getId();
        this.name = course.getName();

        // Asegurarse de que academicPeriod no sea null
        this.academicPeriod = course.getAcademicPeriod() != null 
            ? course.getAcademicPeriod().getName() 
            : null;

        // Convertir lista de Subject a nombres
        this.subjects = course.getSubjects() != null
            ? course.getSubjects().stream()
                .map(Subject::getName)
                .toList()
            : List.of();
    }
}
