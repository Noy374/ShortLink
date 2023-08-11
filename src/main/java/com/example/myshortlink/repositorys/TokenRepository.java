package com.example.myshortlink.repositorys;

import com.example.myshortlink.entity.Token;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {

    @Modifying
    @Query("UPDATE Token u SET u.accessToken = :accessToken ,u.createdDate=:LocalDateTime WHERE u.refreshToken= :refreshToken")
    @Transactional
    void updateAccessToken(@Param("accessToken") String accessToken, @Param("LocalDateTime") LocalDateTime time, @Param("refreshToken") String refreshToken);

    Token findByAccessToken(String token);

    Token findByRefreshToken(String token);
}
