package com.schoolmanager.academic_platform_api.validation;


import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExistsInDatabaseValidator implements ConstraintValidator<ExistsInDatabase, Object> {

    @Autowired
    private EntityManager entityManager;

    private Class<?> domainClass;
    private String fieldName;

    @Override
    public void initialize(ExistsInDatabase constraintAnnotation) {
        this.domainClass = constraintAnnotation.domainClass();
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) return true;

        String jpql = String.format("SELECT COUNT(e) FROM %s e WHERE e.%s = :value", domainClass.getSimpleName(), fieldName);
        Long count = entityManager.createQuery(jpql, Long.class)
            .setParameter("value", value)
            .getSingleResult();
        return count > 0;
    }
}

