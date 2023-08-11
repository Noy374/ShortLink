package com.example.myshortlink.services;


import com.example.myshortlink.entity.Link;
import com.example.myshortlink.repositorys.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkService {
    @Autowired
    LinkRepository linkRepository;

    public  String getLinkById(long parseLong) {
        return linkRepository.getReferenceById(parseLong).getLink();
    }

    public  String getLinkByShortLink(String shortLink){
        return linkRepository.getLinkByShortLink(shortLink).getLink();
    }
    public void  addLink(Link link){
        linkRepository.save(link);
    }
}
