package com.schoolmanager.academic_platform_api.service;

import java.util.List;

import com.schoolmanager.academic_platform_api.dto.StudentCreatedDTO;
import com.schoolmanager.academic_platform_api.dto.StudentGradeResponse;
import com.schoolmanager.academic_platform_api.dto.StudentResponse;
import com.schoolmanager.academic_platform_api.model.Student;

import java.util.Optional;

public interface StudentService {
    List<StudentResponse> getAllStudentResponses();
    Optional<StudentResponse> getStudentResponseById(Long id);
    Optional<Student> createStudent(StudentCreatedDTO student);
    List<StudentGradeResponse> getNotesByStudent(Long id);
    public StudentResponse mapToResponse(Student student);
}
