package com.schoolmanager.academic_platform_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmanager.academic_platform_api.dto.Response.AverageGradeResponse;
import com.schoolmanager.academic_platform_api.dto.Response.FinalCourseReportResponse;
import com.schoolmanager.academic_platform_api.dto.Response.StudentHistoryResponse;
import com.schoolmanager.academic_platform_api.service.ReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportController {
    private ReportService reportService;

    @GetMapping("/average-grades")
    public ResponseEntity<List<AverageGradeResponse>> getAverageGrades() {
        return ResponseEntity.ok(reportService.getAverageGradesByCourseAndSubject());
    }

    @GetMapping("/student-grade-history/{id}")
    public ResponseEntity<List<StudentHistoryResponse>> getStudentGradeHistory(@PathVariable Long id) {
        return ResponseEntity.ok(reportService.getGradeHistoryByStudent(id));
    }

    @GetMapping("/final-report/{courseId}")
    public ResponseEntity<FinalCourseReportResponse> getFinalCourseReport(@PathVariable Long cursoId) {
        return ResponseEntity.ok(reportService.getFinalReportByCourse(cursoId));
    }
}
