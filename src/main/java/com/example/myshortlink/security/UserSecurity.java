package com.example.myshortlink.security;

import com.example.myshortlink.constants.Constants;
import com.example.myshortlink.entity.User;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class UserSecurity {

    public String GenerateAccessToken(User user){
        String value = user.getUsername();
        char[]arr=value.toCharArray();
        StringBuilder str =new StringBuilder();
        Random random = new Random();
        for (int i=0;i<value.length()/2;i++){
            str.append(Math.abs(arr[i]*random.nextInt()));
        }
        return str.toString();
    }
    public String GenerateCookie(User user){
        String cookieValue = user.getUsername() + Constants.RefreshSecurity + user.getPassword();;
        char[]arr=cookieValue.toCharArray();
        StringBuilder str =new StringBuilder();
        for (int i=0,j=cookieValue.length()-1;i<cookieValue.length()/2;i++,j--){
            str.append(arr[i]*2+arr[j]/2);
        }
        return str.toString();
    }
}
