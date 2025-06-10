package com.schoolmanager.academic_platform_api.validation;

import org.springframework.beans.factory.annotation.Autowired;
import com.schoolmanager.academic_platform_api.repository.SubjectRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExistsSubjectIdValidator implements ConstraintValidator<ExistsSubjectId, Long> {
    
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return subjectRepository.existsById(value);
    }
}
