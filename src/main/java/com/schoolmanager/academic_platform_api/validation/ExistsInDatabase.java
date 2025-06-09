package com.schoolmanager.academic_platform_api.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
@Documented
public @interface ExistsInDatabase {
    String message() default "Value does not exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    Class<?> domainClass();
    String fieldName();
}
