package com.example.myshortlink.services;

import com.example.myshortlink.entity.Token;
import com.example.myshortlink.entity.User;
import com.example.myshortlink.repositorys.TokenRepository;
import com.example.myshortlink.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

@Service
public class TokenService {
    @Autowired
    TokenRepository tokenRepository;


    public void createToken(Token token){
        tokenRepository.save(token);
    }


    @Transactional
     public  void deleteTokenByRefreshToken(String token) {
        Token tokenEntity = tokenRepository.findByRefreshToken(token);
        if (tokenEntity != null) {

            tokenRepository.delete(tokenEntity);
        }
    }
    public void updateAccessToken(String accessToken,String refreshToken){
        tokenRepository.updateAccessToken(accessToken,LocalDateTime.now(),refreshToken);
    }
    public Token getTokenByAccessToken(String token){
//        System.out.println(LocalDateTime.now().until(tokenRepository.findByAccessToken(token).getCreatedDate(), ChronoUnit.HOURS));
        if( Math.abs(LocalDateTime.now().until(tokenRepository.findByAccessToken(token).getCreatedDate(), ChronoUnit.HOURS))<7){
            return tokenRepository.findByAccessToken(token);
        }
        return  null;

    }
    public Token getTokenByRefreshToken(String token){
        return tokenRepository.findByRefreshToken(token);
    }
    public boolean checkRefreshToken(String value) {
        return tokenRepository.findByRefreshToken(value)!=null;
    }
}
