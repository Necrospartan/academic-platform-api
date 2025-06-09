package com.schoolmanager.academic_platform_api.validation;

import com.schoolmanager.academic_platform_api.repository.AcademicPeriodRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistsAcademicPeriodIdValidator implements ConstraintValidator<ExistsAcademicPeriodId, Long> {

    @Autowired
    private AcademicPeriodRepository academicPeriodRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; // también puedes dejarlo en true si quieres permitir null
        }
        return academicPeriodRepository.existsById(value);
    }
}
