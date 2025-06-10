package com.schoolmanager.academic_platform_api.dto.Response;

import com.schoolmanager.academic_platform_api.model.Grade;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GradeResponse {
    private Long id;
    private Float value;
    private String remarks;
    private String studentName;
    private String subjectName;
    private String academicPeriodName;
    private String academicPeriodStartDate;
    private String academicPeriodEndDate;

    public GradeResponse(Grade grade) {
        this.id = grade.getId();
        this.value = grade.getValue();
        this.remarks = grade.getRemarks();
        this.studentName = grade.getStudent().getUser().getName();
        this.subjectName = grade.getSubject().getName();
        this.academicPeriodName = grade.getAcademicPeriod().getName();
        this.academicPeriodStartDate = grade.getAcademicPeriod().getStartDate().toString();
        this.academicPeriodEndDate = grade.getAcademicPeriod().getEndDate().toString();
    }
}
