package com.example.myshortlink.repositorys;

import com.example.myshortlink.entity.Link;
import com.example.myshortlink.entity.Token;
import com.example.myshortlink.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> getUserByUsername(String username);

    @Modifying
    @Query("UPDATE User u SET u.token = :token WHERE u.username= :username")
    @Transactional
    void updateToken(@Param("username") String username, @Param("token") Token token);

    User getUserByToken(Token token);
}
