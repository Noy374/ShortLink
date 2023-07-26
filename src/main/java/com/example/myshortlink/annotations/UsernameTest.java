package com.example.myshortlink.annotations;


import com.example.myshortlink.validations.PasswordValidator;
import com.example.myshortlink.validations.UsernameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameValidator.class)
@Documented
public @interface UsernameTest {
    String message() default
            "The username must contain uppercase and lowercase letters, numbers, be at least 6 characters long and be unique";

    Class<?>[] groups() default{};

    Class<? extends Payload>[] payload() default {};
}
