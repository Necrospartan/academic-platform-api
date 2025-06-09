package com.schoolmanager.academic_platform_api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistsAcademicPeriodIdValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsAcademicPeriodId {
    String message() default "Academic period ID does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}