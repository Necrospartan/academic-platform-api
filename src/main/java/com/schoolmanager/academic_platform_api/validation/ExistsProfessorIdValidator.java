package com.schoolmanager.academic_platform_api.validation;

import org.springframework.beans.factory.annotation.Autowired;
import com.schoolmanager.academic_platform_api.repository.ProfessorRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExistsProfessorIdValidator implements ConstraintValidator<ExistsProfessorId, Long> {
    
    @Autowired
    private ProfessorRepository  professorRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; // también puedes dejarlo en true si quieres permitir null
        }
        return professorRepository.existsById(value);
    }
}
