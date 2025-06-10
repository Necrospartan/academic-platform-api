package com.schoolmanager.academic_platform_api.service;

import java.util.List;
import java.util.Optional;

import com.schoolmanager.academic_platform_api.dto.ProfessorCreatedDTO;
import com.schoolmanager.academic_platform_api.dto.Response.ProfessorResponse;
import com.schoolmanager.academic_platform_api.model.Professor;

public interface ProfessorService {
    List<ProfessorResponse> getAllProfessorResponses();
    Optional<ProfessorResponse> getProfessorResponseById(Long id);
    Optional<Professor> createProfessor(ProfessorCreatedDTO professor);
    List<String> getSubjectsByProfessor(Long id);
    // public ProfessorResponse mapToResponse(Professor professor);
}
