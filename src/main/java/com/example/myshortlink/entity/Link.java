package com.example.myshortlink.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@Entity
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true,nullable = false)
    String  link;
    @Column(unique = true,nullable = false)
    String shortLink;
    @ManyToMany(mappedBy = "links")
    List<User> usersList=new ArrayList<>() ;

}
