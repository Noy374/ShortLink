package com.example.myshortlink.controllers;

import com.example.myshortlink.entity.Link;
import com.example.myshortlink.entity.Token;
import com.example.myshortlink.payload.request.LinkRequest;
import com.example.myshortlink.security.UserSecurity;
import com.example.myshortlink.services.LinkService;
import com.example.myshortlink.services.TokenService;
import com.example.myshortlink.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LinkController {

    private final TokenService tokenService;
    private final UserService userService;
    private final LinkService linkService;
    private final UserSecurity userSecurity=new UserSecurity();

    public LinkController(TokenService tokenService, UserService userService, LinkService linkService) {
        this.tokenService = tokenService;
        this.userService = userService;
        this.linkService = linkService;
    }

    @GetMapping("/shortLink/{value}")
    ResponseEntity<Object> getLink(@PathVariable String value){
        String link=linkService.getLinkByShortLink("http://localhost:8080/shortLink/"+value);
        return ResponseEntity.ok("{\"link:\":\""+link+"\"}");
    }


    @PostMapping("/addLink")
    ResponseEntity<Object> addLink(@RequestBody LinkRequest linkRequest, HttpServletRequest request) {


        Token token1 = tokenService.getTokenByAccessToken(linkRequest.getAccessToken());
        if (token1 != null) {
            Link link1=new Link();
            link1.setLink(linkRequest.getLink());
            link1.setShortLink("http://localhost:8080/shortLink/"+linkRequest.getShortLinkName());
            link1.setUser(userService.getUserByUsername(linkRequest.getUsername()));
            linkService.addLink(link1);


            return ResponseEntity.ok("{\"shortLink\":\""+link1.getShortLink()+"\"}");
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
                }
            }
        }
        return ResponseEntity.badRequest().body(HttpStatus.UNAUTHORIZED);
    }
}
