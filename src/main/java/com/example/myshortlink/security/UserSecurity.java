package com.example.myshortlink.security;

import com.example.myshortlink.constants.Constants;
import com.example.myshortlink.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserSecurity {

    public boolean TestRefreshToken(String token){
        return true;
    }
    public boolean TestAccessToken(String token){
        return true;
    }
    public String GenerateAccessToken(User user){
        String cookieValue = user.getUsername() + Constants.AccessSecurity + user.getPassword();;
        char[]arr=cookieValue.toCharArray();
        StringBuilder str =new StringBuilder();
        for (int i=0,j=cookieValue.length()-1;i<cookieValue.length()/2;i++,j--){
            str.append(arr[i]*2+arr[j]/2);
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
