package com.imti.UserRegistrationAndLoginSystem.annotation;


import com.imti.UserRegistrationAndLoginSystem.validation.CustomEmailValidator;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomEmailValidator.class)
@Documented
public @interface ValidEmail {

    String message() default "Invalid email";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
