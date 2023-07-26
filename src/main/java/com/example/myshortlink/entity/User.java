package com.example.myshortlink.entity;

import com.example.myshortlink.annotations.PasswordTest;
import com.example.myshortlink.annotations.UsernameTest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Сan'not be empty")
    @Column(unique = true,nullable = false)
    @Size(min=6,max=200,message ="Must be between 6 and 200 characters" )
    @UsernameTest
    String username;
    @NotBlank(message = "Сan'not be empty")
    @Size(min=6,max=200,message = "must be between 6 and 200 characters")
    @Column(nullable = false,length = 200)
    @PasswordTest
    String password;
    @ManyToMany
    @JoinTable(
            name = "user_link",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "link_id")
    )
    List<Link> links=new ArrayList<>();
}
