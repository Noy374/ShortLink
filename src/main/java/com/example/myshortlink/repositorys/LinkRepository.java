package com.example.myshortlink.repositorys;


import com.example.myshortlink.entity.Link;
import com.example.myshortlink.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkRepository extends JpaRepository<Link,Long> {

    Link getLinkByShortLink(String shortLink);


}
