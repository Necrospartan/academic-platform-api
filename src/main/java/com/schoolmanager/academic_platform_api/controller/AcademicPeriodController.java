package com.schoolmanager.academic_platform_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmanager.academic_platform_api.dto.AcademicPeriodCreatedDTO;
import com.schoolmanager.academic_platform_api.model.AcademicPeriod;
import com.schoolmanager.academic_platform_api.service.AcademicPeriodService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/periods")
@RequiredArgsConstructor
public class AcademicPeriodController {
    @Autowired
    private AcademicPeriodService academicPeriodService;

    @GetMapping
    public ResponseEntity<List<AcademicPeriod>> getAllAcademicPeriods() {
        return ResponseEntity.ok(academicPeriodService.getAllAcademicPeriods());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcademicPeriod> getAcademicPeriodById(@PathVariable Long id) {
        return academicPeriodService.getAcademicPeriodById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<AcademicPeriod> createAcademicPeriod(@RequestBody @Valid AcademicPeriodCreatedDTO academicPeriod) {
        return academicPeriodService.createAcademicPeriod(academicPeriod)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.internalServerError().build());
    }
}
