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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private  ResponseErrorValidation responseErrorValidation;
    private final TokenService tokenService;
    private final UserService userService;
    private final UserSecurity userSecurity=new UserSecurity();

    @Autowired
    public UserController(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @PostMapping("/links")
    ResponseEntity<Object> allLinks(@RequestBody Token token,HttpServletRequest request) {

        Token token1 = tokenService.getTokenByAccessToken(token.getAccessToken());
        if (token1 != null) {
            return ResponseEntity.ok(userService.getLinksByToken(token1).toString());
        } else {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                if (tokenService.checkRefreshToken(cookies[0].getValue())) {
                    String s = userSecurity.
                            GenerateAccessToken(userService.
                                    getUserByToken(tokenService.
                                            getTokenByRefreshToken(cookies[0].getValue())));
                    tokenService.updateAccessToken(s,cookies[0].getValue());
                    return ResponseEntity
                            .ok("{\"accessToken\": \"" + s + "\"}");
                }else return ResponseEntity.badRequest().body(HttpStatus.UNAUTHORIZED);
            }else return ResponseEntity.badRequest().body(HttpStatus.UNAUTHORIZED);
        }



    }
}
