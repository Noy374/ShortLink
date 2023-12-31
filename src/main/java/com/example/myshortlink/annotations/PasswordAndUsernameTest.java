package com.example.myshortlink.annotations;

import com.example.myshortlink.validations.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
@Documented
public @interface PasswordAndUsernameTest {
    String message() default "The password and username must contain uppercase and lowercase letters, numbers and be at least 6 characters long";

    Class<?>[] groups() default{};

    Class<? extends Payload>[] payload() default {};
}
