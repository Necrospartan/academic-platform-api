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

import com.schoolmanager.academic_platform_api.dto.StudentCreatedDTO;
import com.schoolmanager.academic_platform_api.dto.StudentGradeResponse;
import com.schoolmanager.academic_platform_api.dto.StudentResponse;
import com.schoolmanager.academic_platform_api.model.Student;
import com.schoolmanager.academic_platform_api.service.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity <List<StudentResponse>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudentResponses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable Long id) {
        return studentService.getStudentResponseById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@RequestBody @Valid StudentCreatedDTO request) {
        Student student = studentService.createStudent(request)
            .orElseThrow(() -> new RuntimeException("Failed to create student"));
        return ResponseEntity.ok(studentService.mapToResponse(student));
    }

    @GetMapping("/{id}/notes")
    public ResponseEntity<List<StudentGradeResponse>> getNotesByStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getNotesByStudent(id));
    }
}
