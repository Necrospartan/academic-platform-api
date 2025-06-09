package com.schoolmanager.academic_platform_api.service;

import java.util.List;
import java.util.Optional;

import com.schoolmanager.academic_platform_api.dto.CourseCreatedDTO;
import com.schoolmanager.academic_platform_api.dto.CourseResponse;
import com.schoolmanager.academic_platform_api.model.Course;

public interface CourseService {
    List<CourseResponse> getAllCourseResponses();
    Optional<CourseResponse> getCourseResponsesByProfessor(Long id);
    Optional<Course> createCourse(CourseCreatedDTO course);
}
