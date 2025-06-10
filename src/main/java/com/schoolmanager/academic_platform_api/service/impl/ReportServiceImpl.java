package com.schoolmanager.academic_platform_api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolmanager.academic_platform_api.dto.Response.AverageGradesResponse;
import com.schoolmanager.academic_platform_api.dto.Response.FinalCourseReportResponse;
import com.schoolmanager.academic_platform_api.dto.Response.GradeResponse;
import com.schoolmanager.academic_platform_api.dto.Response.StudentHistoryResponse;
import com.schoolmanager.academic_platform_api.model.Grade;
import com.schoolmanager.academic_platform_api.repository.CourseRepository;
import com.schoolmanager.academic_platform_api.repository.GradeRepository;
import com.schoolmanager.academic_platform_api.repository.SubjectRepository;
import com.schoolmanager.academic_platform_api.service.ReportService;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService{
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private GradeRepository gradeRepository;

    public List<AverageGradesResponse> calculateAverageByCourseAndSubject() {
        return courseRepository.calculateAverageByCourseAndSubject();
    }

    public List<StudentHistoryResponse> getCompleteStudentHistory(Long studentId) {
        return gradeRepository.findByStudentId(studentId).stream()
        .map(StudentHistoryResponse::new).collect(Collectors.toList());
    }

    public List<FinalCourseReportResponse> generateFinalReportByCourse(Long courseId){
        return subjectRepository.generateFinalReportByCourse(courseId); 
    }
}
