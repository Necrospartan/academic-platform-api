package com.schoolmanager.academic_platform_api.service;

import java.util.List;

import com.schoolmanager.academic_platform_api.dto.Response.AverageGradesResponse;
import com.schoolmanager.academic_platform_api.dto.Response.FinalCourseReportResponse;
import com.schoolmanager.academic_platform_api.dto.Response.StudentHistoryResponse;

public interface ReportService {
    List<AverageGradesResponse> calculateAverageByCourseAndSubject();
    List<StudentHistoryResponse> getCompleteStudentHistory(Long studentId);
    List<FinalCourseReportResponse> generateFinalReportByCourse(Long courseId);
}
