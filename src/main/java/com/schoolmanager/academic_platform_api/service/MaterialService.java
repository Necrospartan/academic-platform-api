package com.schoolmanager.academic_platform_api.service;

import java.util.List;
import java.util.Optional;

import com.schoolmanager.academic_platform_api.dto.MaterialCreatedDTO;
import com.schoolmanager.academic_platform_api.dto.Response.MaterialResponse;

public interface MaterialService {
    Optional<MaterialResponse> createMaterial( MaterialCreatedDTO material);
    List<MaterialResponse> getAllMaterialBySubject(Long subjectId);
    Boolean deleteMaterial(Long id);
}
