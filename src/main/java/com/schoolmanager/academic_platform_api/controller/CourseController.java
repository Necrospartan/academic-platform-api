package com.schoolmanager.academic_platform_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmanager.academic_platform_api.dto.CourseCreatedDTO;
import com.schoolmanager.academic_platform_api.dto.CourseResponse;
import com.schoolmanager.academic_platform_api.model.Course;
import com.schoolmanager.academic_platform_api.service.CourseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllCourse(){
        return ResponseEntity.ok(courseService.getAllCourseResponses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourseById(Long id){
        return courseService.getCourseResponsesByProfessor(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody @Valid CourseCreatedDTO course){
        return courseService.createCourse(course)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.internalServerError().build());
    }
}
