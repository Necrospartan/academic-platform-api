package com.schoolmanager.academic_platform_api.validation;
import com.schoolmanager.academic_platform_api.dto.AcademicPeriodCreatedDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidDateRangeValidator implements ConstraintValidator<ValidDateRange, AcademicPeriodCreatedDTO> {

    @Override
    public boolean isValid(AcademicPeriodCreatedDTO dto, ConstraintValidatorContext context) {
        if (dto.getStartDate() == null || dto.getEndDate() == null) {
            return true; // otros @NotNull/@NotBlank se encargan
        }

        if (dto.getEndDate().isBefore(dto.getStartDate())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("End date must be after start date")
                .addPropertyNode("endDate")
                .addConstraintViolation();
            return false;
        }

        return true;
    }
}
