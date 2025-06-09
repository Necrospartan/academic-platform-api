package com.schoolmanager.academic_platform_api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.schoolmanager.academic_platform_api.dto.CourseCreatedDTO;
import com.schoolmanager.academic_platform_api.dto.CourseResponse;
import com.schoolmanager.academic_platform_api.model.AcademicPeriod;
import com.schoolmanager.academic_platform_api.model.Course;
import com.schoolmanager.academic_platform_api.repository.AcademicPeriodRepository;
import com.schoolmanager.academic_platform_api.repository.CourseRepository;
import com.schoolmanager.academic_platform_api.service.CourseService;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private AcademicPeriodRepository academicPeriodRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponse> getAllCourseResponses() {
        return courseRepository.findAll().stream().map(CourseResponse::new).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CourseResponse> getCourseResponsesByProfessor(Long id) {
        return courseRepository.findById(id).map(CourseResponse::new);
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
