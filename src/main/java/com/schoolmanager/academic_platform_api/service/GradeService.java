package com.schoolmanager.academic_platform_api.service;

import java.util.List;
import java.util.Optional;

import com.schoolmanager.academic_platform_api.dto.GradeCreatedDTO;
import com.schoolmanager.academic_platform_api.dto.GradeUpdateDTO;
import com.schoolmanager.academic_platform_api.dto.Response.GradeResponse;

public interface GradeService {
    Optional<GradeResponse> createGrade(GradeCreatedDTO grade);
    List<GradeResponse> getAllGradeBySubject(Long subjectId);
    List<GradeResponse> getAllGradeByStudent(Long studentId);
    Optional<GradeResponse> updateGrade(Long id, GradeUpdateDTO grade);
    Boolean deleteGrade(Long id);
}
