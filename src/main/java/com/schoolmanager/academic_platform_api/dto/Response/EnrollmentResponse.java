package com.schoolmanager.academic_platform_api.dto.Response;

import com.schoolmanager.academic_platform_api.model.Enrollment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnrollmentResponse {
    private Long id;
    private String studentName;
    private String courseName;
    private String academicPeriodName;
    private String academicPeriodStartDate;
    private String academicPeriodEndDate;

    public EnrollmentResponse(Enrollment enrollment) {
        this.id = enrollment.getId();
        this.studentName = enrollment.getStudent().getUser().getName();
        this.courseName = enrollment.getCourse().getName();
        this.academicPeriodName = enrollment.getAcademicPeriod().getName();
        this.academicPeriodStartDate = enrollment.getAcademicPeriod().getStartDate().toString();
        this.academicPeriodEndDate = enrollment.getAcademicPeriod().getEndDate().toString();
    }
}
