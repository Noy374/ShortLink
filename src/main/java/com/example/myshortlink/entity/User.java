package com.example.myshortlink.entity;

import com.example.myshortlink.annotations.PasswordAndUsernameTest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Сan't be empty")
    @Column(unique = true,nullable = false)
    @Size(min=6,max=200,message ="Must be between 6 and 200 characters" )
    @PasswordAndUsernameTest
    private String username;
    @NotBlank(message = "Сan't be empty")
    @Size(min=6,max=200,message = "must be between 6 and 200 characters")
    @Column(nullable = false,length = 200)
    @PasswordAndUsernameTest
    private String password;
    @OneToMany(mappedBy = "user")
    private  List<Link> links;
    @OneToOne(optional = true)
    @JoinColumn(name = "token_id",referencedColumnName = "id")
    private Token token ;

    @Override
    public String toString(){
        return "[username="+username+",password="+password+"]";
    }
}
