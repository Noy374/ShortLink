package com.example.myshortlink.validations;

import com.example.myshortlink.annotations.PasswordTest;
import com.example.myshortlink.annotations.UsernameTest;
import com.example.myshortlink.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsernameValidator implements ConstraintValidator<UsernameTest,Object> {
    public UsernameValidator() {}
    @Override
    public void initialize(UsernameTest constraintAnnotation) {

    }

    private UserService userService;

    @Autowired
    public void setService(UserService userService){
        this.userService=userService;
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String username=(String) o;
        if (username.length() < 6) {
            return false;
        }
           if(userService.getUserByUsername(username)!=null) return false;
        else {
            boolean hasUpperCase = false;
            boolean hasLowerCase = false;
            boolean hasDigit = false;
            for (char c : username.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    hasUpperCase = true;
                } else if (Character.isLowerCase(c)) {
                    hasLowerCase = true;
                } if (Character.isDigit(c)) {
                    hasDigit = true;
                }
                if(hasDigit && hasUpperCase && hasLowerCase) return  true;
            }
        }

        return false;
    }
}
