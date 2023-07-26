package com.example.myshortlink.services;


import com.example.myshortlink.entity.User;
import com.example.myshortlink.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        userRepository.save(user);
    }
    public User getUserByUsername(String username){
        Optional<User> user = userRepository.findUserByUsername(username);
        if (user.isEmpty()) return null;
        return user.get();
    }
}
