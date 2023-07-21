package com.pinkieyun.fitnesscenter.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pinkieyun.fitnesscenter.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = "SELECT t FROM Token t INNER JOIN Account a ON t.account.id = a.id WHERE t.account.id = :id AND (t.expired = FALSE OR t.revoked = FALSE)")
    List<Token> findAllValidTokenByAccount(@Param("id") Integer id);
    
    Optional<Token> findByToken(String token);
}
