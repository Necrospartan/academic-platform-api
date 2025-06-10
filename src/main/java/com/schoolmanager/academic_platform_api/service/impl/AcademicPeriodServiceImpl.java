package com.schoolmanager.academic_platform_api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.schoolmanager.academic_platform_api.dto.AcademicPeriodCreatedDTO;
import com.schoolmanager.academic_platform_api.model.AcademicPeriod;
import com.schoolmanager.academic_platform_api.repository.AcademicPeriodRepository;
import com.schoolmanager.academic_platform_api.service.AcademicPeriodService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AcademicPeriodServiceImpl implements AcademicPeriodService{
    
    @Autowired
    private AcademicPeriodRepository academicPeriodRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AcademicPeriod> getAllAcademicPeriods() {
        return academicPeriodRepository.findAll().stream()
        .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AcademicPeriod> getAcademicPeriodById(Long id) {
        return academicPeriodRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<AcademicPeriod> createAcademicPeriod(AcademicPeriodCreatedDTO academicPeriod) {
        AcademicPeriod newAcademicPeriod = new AcademicPeriod();
        newAcademicPeriod.setName(academicPeriod.getName());
        newAcademicPeriod.setStartDate(academicPeriod.getStartDate());
        newAcademicPeriod.setEndDate(academicPeriod.getEndDate());
        return Optional.of(academicPeriodRepository.save(newAcademicPeriod));
    }

}
