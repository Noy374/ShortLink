package com.example.myshortlink.controllers;

import com.example.myshortlink.entity.Token;
import com.example.myshortlink.entity.User;
import com.example.myshortlink.helper.Message;
import com.example.myshortlink.security.UserSecurity;
import com.example.myshortlink.services.TokenService;
import com.example.myshortlink.services.UserService;
import com.example.myshortlink.validations.ResponseErrorValidation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RegistrationController {
    @Autowired
    private ResponseErrorValidation responseErrorValidation;
    private final TokenService tokenService;
    private final UserService userService;
    private final UserSecurity userSecurity=new UserSecurity();

    public RegistrationController(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }


    @PostMapping("/registration")
    ResponseEntity<Object> registration(@Valid @RequestBody User user, BindingResult bindingResult){
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        if(userService.getUserByUsername(user.getUsername())!=null) return ResponseEntity.badRequest().
                body(new Message("User with this username already exists"));
        userService.createUser(user);
        return ResponseEntity.ok(new Message("User registered successfully!"));
    }

    @PostMapping("/signIn")
    ResponseEntity<Object> signIn(@Valid @RequestBody User user, BindingResult bindingResult, HttpServletResponse response) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        if (userService.getUserByUsername(user.getUsername()) == null)
            return ResponseEntity.badRequest().body(new Message(HttpStatus.UNAUTHORIZED.name()));
        Token token=new Token();
        token.setAccessToken(userSecurity.GenerateAccessToken(user));
        token.setRefreshToken(userSecurity.GenerateCookie(user));
        user.setToken(token);
        tokenService.createToken(token);
        userService.updateToken(user);
        String value = userSecurity.GenerateCookie(user);
        Cookie cookie = new Cookie("refreshToken", value);
        int cookieMaxAge = 7 * 24 * 60 * 60; // 7 дней в секундах
        cookie.setMaxAge(cookieMaxAge);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return ResponseEntity.ok("{\"accessToken\": \""+userSecurity.GenerateAccessToken(user)+"\"}");
    }
    @PostMapping("/signOut")
    ResponseEntity<Object> signOut(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null)tokenService.deleteTokenByRefreshToken(cookies[0].getValue());

        return ResponseEntity.ok("You have logged out of your account");
    }
}
