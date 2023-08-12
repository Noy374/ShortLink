package com.example.myshortlink.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "token")
    private User user;


    private String accessToken;
    private String refreshToken;
    @Column(updatable = false)
    private LocalDateTime createdDate;
    @PrePersist
    protected void onCreate()
    {
        this.createdDate = LocalDateTime.now();
    }

    @Override
    public String toString(){
        return "[accessToken="+accessToken+",refreshToken="+refreshToken+"]";
    }
}
