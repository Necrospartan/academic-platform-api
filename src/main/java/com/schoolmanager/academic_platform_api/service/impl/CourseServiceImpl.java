package com.schoolmanager.academic_platform_api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.schoolmanager.academic_platform_api.dto.CourseCreatedDTO;
import com.schoolmanager.academic_platform_api.model.AcademicPeriod;
import com.schoolmanager.academic_platform_api.model.Course;
import com.schoolmanager.academic_platform_api.repository.AcademicPeriodRepository;
import com.schoolmanager.academic_platform_api.repository.CourseRepository;
import com.schoolmanager.academic_platform_api.service.CourseService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private AcademicPeriodRepository academicPeriodRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Course> getAllCourseResponses() {
        return courseRepository.findAll().stream()
        .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Course> getCourseResponsesById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Course> createCourse(CourseCreatedDTO course) {
        Course newCourse = new Course();
        newCourse.setName(course.getName());
        AcademicPeriod academicPeriod = academicPeriodRepository.findById(course.getAcademicPeriodId())
            .orElseThrow(() -> new RuntimeException("Academic period not found with id: " + course.getAcademicPeriodId()));
        newCourse.setAcademicPeriod(academicPeriod);
        return Optional.of(courseRepository.save(newCourse));
    }
}
