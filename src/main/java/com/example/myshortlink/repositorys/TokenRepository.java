package com.example.myshortlink.repositorys;

import com.example.myshortlink.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {




    Token findByRefreshToken(String token);
}
