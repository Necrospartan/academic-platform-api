package com.schoolmanager.academic_platform_api.service;

import java.util.List;

import com.schoolmanager.academic_platform_api.dto.SubjectCreatedDTO;
import com.schoolmanager.academic_platform_api.dto.Response.SubjectResponse;
import com.schoolmanager.academic_platform_api.model.Subject;
import java.util.Optional;
public interface SubjectService {
    List<SubjectResponse> getAllSubject();
    Optional<SubjectResponse> getSubjectById(Long id);
    Optional<SubjectResponse> createSubject(SubjectCreatedDTO subject);
    Optional<Subject> updateSubject(Long id, SubjectCreatedDTO subject);
    boolean deleteSubject(Long id);
}
