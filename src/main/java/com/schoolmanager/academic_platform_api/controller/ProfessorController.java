package com.schoolmanager.academic_platform_api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.schoolmanager.academic_platform_api.dto.ProfessorCreatedDTO;
import com.schoolmanager.academic_platform_api.dto.Response.ProfessorResponse;
import com.schoolmanager.academic_platform_api.model.Professor;
import com.schoolmanager.academic_platform_api.service.ProfessorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/professors")
@RequiredArgsConstructor
public class ProfessorController {
    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public ResponseEntity<List<ProfessorResponse>> getAllProfessors() {
        return ResponseEntity.ok(professorService.getAllProfessorResponses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponse> getProfessorById(@PathVariable Long id) {
        return professorService.getProfessorResponseById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Professor> createProfessor(@RequestBody @Valid ProfessorCreatedDTO request) {
        Optional<Professor> professor = professorService.createProfessor(request);
        return professor.map(ResponseEntity::ok)
        .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}/assignature")
    public ResponseEntity<List<String>> getSubjectsByProfessor(@PathVariable Long id) {
        return ResponseEntity.ok(professorService.getSubjectsByProfessor(id));
    }
}
