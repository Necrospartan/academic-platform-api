package com.schoolmanager.academic_platform_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmanager.academic_platform_api.dto.GradeCreatedDTO;
import com.schoolmanager.academic_platform_api.dto.GradeUpdateDTO;
import com.schoolmanager.academic_platform_api.dto.Response.GradeResponse;
import com.schoolmanager.academic_platform_api.service.GradeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/grades")
@RequiredArgsConstructor
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @PostMapping
    public ResponseEntity<GradeResponse> createGrade(@RequestBody @Valid GradeCreatedDTO grade) {
        return gradeService.createGrade(grade)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.internalServerError().build());
    }

    @GetMapping("/Subject/{id}")
    public ResponseEntity<List<GradeResponse>> getAllGradeBySubject(@PathVariable Long id) {
        return ResponseEntity.ok(gradeService.getAllGradeBySubject(id));
    }

    @GetMapping("/Student/{id}")
    public ResponseEntity<List<GradeResponse>> getAllGradeByStudent(@PathVariable Long id) {
        return ResponseEntity.ok(gradeService.getAllGradeByStudent(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GradeResponse> updateGrade(@PathVariable Long id, @RequestBody @Valid GradeUpdateDTO grade) {
        return gradeService.updateGrade(id, grade)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
        Boolean deleted = gradeService.deleteGrade(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
