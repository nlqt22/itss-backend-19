package com.pinkieyun.fitnesscenter.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pinkieyun.fitnesscenter.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query(value = "SELECT a FROM Account a WHERE a.username = :username")
    Optional<Account> findByUsername(String username);

}
