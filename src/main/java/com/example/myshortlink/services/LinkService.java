package com.example.myshortlink.services;


import com.example.myshortlink.repositorys.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkService {
    @Autowired
    LinkRepository linkRepository;
}
