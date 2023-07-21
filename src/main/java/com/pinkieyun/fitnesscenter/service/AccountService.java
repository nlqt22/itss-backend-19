package com.pinkieyun.fitnesscenter.service;

import java.util.Optional;

import com.pinkieyun.fitnesscenter.entity.Account;
import com.pinkieyun.fitnesscenter.entity.dto.AccountDTO;
import com.pinkieyun.fitnesscenter.entity.dto.AccountFormDTO;

public interface AccountService {

    Optional<Account> findByUsername(String username);
    
    Optional<Account> findOne(Integer id);

    Optional<AccountDTO> findOneDTO(Integer id);

    Account save(Account account);

    Optional<AccountDTO> update(Integer id, AccountFormDTO accountFormDTO);

    Account update(Integer id, Account account);

    Account changeActive(Integer id);
}
