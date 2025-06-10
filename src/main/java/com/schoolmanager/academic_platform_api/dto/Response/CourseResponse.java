package com.schoolmanager.academic_platform_api.dto.Response;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

import com.schoolmanager.academic_platform_api.model.Course;

@Getter
@Setter
public class CourseResponse {
    private Long id;
    private String name;
    private String academicPeriodName;
    private List<String> subjects;
    
    public CourseResponse(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.academicPeriodName = course.getAcademicPeriod() != null 
            ? course.getAcademicPeriod().getName() 
            : null;
        this.subjects = course.getSubjects() != null
            ? course.getSubjects().stream()
                .map(subject -> subject.getName() + " - " + subject.getProfessor().getUser().getName())
                .toList()
            : List.of();
    }
}
