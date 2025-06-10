package com.schoolmanager.academic_platform_api.service;

import java.util.List;

import com.schoolmanager.academic_platform_api.dto.Response.AverageGradeResponse;
import com.schoolmanager.academic_platform_api.dto.Response.FinalCourseReportResponse;
import com.schoolmanager.academic_platform_api.dto.Response.StudentHistoryResponse;

public interface ReportService {
    List<AverageGradeResponse> getAverageGradesByCourseAndSubject();
    List<StudentHistoryResponse> getGradeHistoryByStudent(Long studentId);
    FinalCourseReportResponse getFinalReportByCourse(Long courseId);
}
