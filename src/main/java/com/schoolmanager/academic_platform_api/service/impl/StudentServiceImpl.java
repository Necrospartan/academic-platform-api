package com.schoolmanager.academic_platform_api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.schoolmanager.academic_platform_api.dto.StudentCreatedDTO;
import com.schoolmanager.academic_platform_api.dto.Response.StudentGradeResponse;
import com.schoolmanager.academic_platform_api.dto.Response.StudentResponse;
import com.schoolmanager.academic_platform_api.model.AcademicPeriod;
import com.schoolmanager.academic_platform_api.model.Course;
import com.schoolmanager.academic_platform_api.model.Enrollment;
import com.schoolmanager.academic_platform_api.model.Role;
import com.schoolmanager.academic_platform_api.model.Student;
import com.schoolmanager.academic_platform_api.model.User;
import com.schoolmanager.academic_platform_api.repository.AcademicPeriodRepository;
import com.schoolmanager.academic_platform_api.repository.CourseRepository;
import com.schoolmanager.academic_platform_api.repository.EnrollmentRepository;
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
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private AcademicPeriodRepository academicPeriodRepository;

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

        Optional<Role> role = roleRepository.findByName("ESTUDIANTE");

        Optional<AcademicPeriod> academicPeriod = academicPeriodRepository.findById(student.getPeriodId());
        Optional<Course> course = courseRepository.findById(student.getCourseId());

        if (role.isPresent() && academicPeriod.isPresent() && course.isPresent()) {
            user.setRole(role.get());
            Optional<User> savedUser = userService.createUser(user);
            // Crear estudiante
            Student newStudent = new Student();
            newStudent.setUser(savedUser.get());
            Student saveStudent = studentRepository.save(newStudent);
            // Crear matricula
            Enrollment enrollment = new Enrollment();
            enrollment.setStudent(saveStudent);
            enrollment.setAcademicPeriod(academicPeriod.get());
            enrollment.setCourse(course.get());
            enrollmentRepository.save(enrollment);
            return Optional.of(new StudentResponse(saveStudent));
        }
        return Optional.empty();
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
