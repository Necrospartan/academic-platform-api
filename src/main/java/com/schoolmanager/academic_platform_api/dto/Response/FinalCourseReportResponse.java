package com.schoolmanager.academic_platform_api.dto.Response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FinalCourseReportResponse {
    private String courseName;
    private String academicPeriod;
    private List<StudentFinalReportResponse> students;
}
