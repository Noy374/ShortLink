package com.example.myshortlink.services;

import com.example.myshortlink.entity.Token;
import com.example.myshortlink.entity.User;
import com.example.myshortlink.repositorys.TokenRepository;
import com.example.myshortlink.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TokenService {
    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    UserRepository userRepository;
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
}
