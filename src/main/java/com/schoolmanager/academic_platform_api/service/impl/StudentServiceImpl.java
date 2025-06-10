package com.schoolmanager.academic_platform_api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.schoolmanager.academic_platform_api.dto.StudentCreatedDTO;
import com.schoolmanager.academic_platform_api.dto.Response.StudentGradeResponse;
import com.schoolmanager.academic_platform_api.dto.Response.StudentResponse;
import com.schoolmanager.academic_platform_api.model.Role;
import com.schoolmanager.academic_platform_api.model.Student;
import com.schoolmanager.academic_platform_api.model.User;
import com.schoolmanager.academic_platform_api.repository.RoleRepository;
import com.schoolmanager.academic_platform_api.repository.StudentRepository;
import com.schoolmanager.academic_platform_api.service.StudentService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserServiceImpl userService;
    private RoleRepository roleRepository;

    // public StudentResponse mapToResponse(Student student) {
    //     StudentResponse response = new StudentResponse();
    //     response.setId(student.getId());
    //     //Faltan datos al response

    //     if (student.getUser() != null) {
    //         response.setUserName(student.getUser().getName());
    //         response.setUserEmail(student.getUser().getEmail());
    //         response.setUserRole(student.getUser().getRole());
    //     }

    //     return response;
    // }

    @Override
    @Transactional(readOnly = true)
    public List<StudentResponse> getAllStudentResponses() {
        return studentRepository.findAll().stream()
            .map(StudentResponse::new)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StudentResponse> getStudentResponseById(Long id) {
        return studentRepository.findById(id)
            .map(StudentResponse::new);
    }

    @Override
    @Transactional
    public Optional<StudentResponse> createStudent(StudentCreatedDTO student) {
        // Crear usuario
        User user = new User();
        user.setName(student.getName());
        user.setEmail(student.getEmail());
        user.setPassword(student.getPassword());

        Role role = roleRepository.findByName("ESTUDIANTE")
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRole(role);

        User savedUser = userService.createUser(user).orElseThrow(() -> new RuntimeException("User creation failed"));

        // Crear estudiante
        Student newStudent = new Student();
        newStudent.setUser(savedUser);

        return Optional.of(new StudentResponse(studentRepository.save(newStudent)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentGradeResponse> getNotesByStudent(Long id) {
        return studentRepository.findById(id)
            .map(student -> student.getGrades().stream()
                .map(grade -> new StudentGradeResponse(
                    grade.getValue(), // el valor de la nota
                    grade.getSubject().getName() // nombre de la asignatura
                ))
                .collect(Collectors.toList())
            ).orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }
}
