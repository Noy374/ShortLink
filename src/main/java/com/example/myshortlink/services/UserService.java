package com.example.myshortlink.services;


import com.example.myshortlink.entity.User;
import com.example.myshortlink.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Component
public class UserService {

    @Autowired
    private  UserRepository userRepository;

    public void createUser(User user) {
        userRepository.save(user);
    }
    public User getUserByUsername(String username){
        Optional<User> user = userRepository.getUserByUsername(username);
        if (user.isEmpty()) return null;
        return user.get();
    }

    public void updateToken(User user) {
        userRepository.updateToken(user.getUsername(), user.getToken());
    }
}
