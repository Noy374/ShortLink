package com.example.myshortlink.validations;

import com.example.myshortlink.annotations.PasswordAndUsernameTest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordAndUsernameTest,Object> {
    @Override
    public void initialize(PasswordAndUsernameTest constraintAnnotation) {

    }
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String password=(String) o;
        if (password.length() < 6) {
            return false;
        }
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } if (Character.isDigit(c)) {
                hasDigit = true;
            }
            if(hasDigit && hasUpperCase && hasLowerCase) return  true;
        }


        return false;

    }
}
