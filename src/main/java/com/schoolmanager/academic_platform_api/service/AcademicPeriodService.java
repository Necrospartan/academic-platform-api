package com.schoolmanager.academic_platform_api.service;

import java.util.List;
import java.util.Optional;

import com.schoolmanager.academic_platform_api.dto.AcademicPeriodCreatedDTO;
import com.schoolmanager.academic_platform_api.model.AcademicPeriod;

public interface AcademicPeriodService {
    List<AcademicPeriod> getAllAcademicPeriods();
    Optional<AcademicPeriod> getAcademicPeriodById(Long id);
    Optional<AcademicPeriod> createAcademicPeriod(AcademicPeriodCreatedDTO academicPeriod);
}
