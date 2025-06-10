package com.schoolmanager.academic_platform_api.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.schoolmanager.academic_platform_api.repository.CourseRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExistsCourseIdValidator implements ConstraintValidator<ExistsCourseId, Long> {
    @Autowired
    private CourseRepository courseRepository;
    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return courseRepository.existsById(value);
    }
}
