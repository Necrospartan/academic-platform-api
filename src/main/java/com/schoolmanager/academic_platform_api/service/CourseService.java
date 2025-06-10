package com.schoolmanager.academic_platform_api.service;

import java.util.List;
import java.util.Optional;

import com.schoolmanager.academic_platform_api.dto.CourseCreatedDTO;
import com.schoolmanager.academic_platform_api.model.Course;

public interface CourseService {
    List<Course> getAllCourseResponses();
    Optional<Course> getCourseResponsesById(Long id);
    Optional<Course> createCourse(CourseCreatedDTO course);
}
