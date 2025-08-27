package com.app.crud.products;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * Custom annotation to ensure that a Product name is unique.
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = UniqueProductNameContraintValidator.class)
public @interface UniqueProductNameConstraint {

    // Default error message
    String message() default "Product name must be unique";

    // Allows specification of validation groups
    Class<?>[] groups() default {};

    // Allows specification of payloads
    Class<? extends Payload>[] payload() default {};
}
