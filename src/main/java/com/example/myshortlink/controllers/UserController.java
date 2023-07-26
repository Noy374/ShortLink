package com.example.myshortlink.controllers;

import com.example.myshortlink.entity.User;
import com.example.myshortlink.helper.Message;
import com.example.myshortlink.services.UserService;
import com.example.myshortlink.validations.ResponseErrorValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private  ResponseErrorValidation responseErrorValidation;

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/registration")
    ResponseEntity<Object>  registration(@Valid @RequestBody User user, BindingResult bindingResult){
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        userService.createUser(user);
        return ResponseEntity.ok(new Message("User registered successfully!"));
    }

    @PostMapping("/signIn")
    ResponseEntity<Object>  signIn(@Valid @RequestBody User user, BindingResult bindingResult){
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        userService.createUser(user);
        return ResponseEntity.ok(new Message("User registered successfully!"));
    }

}
