package com.pinkieyun.fitnesscenter.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.pinkieyun.fitnesscenter.entity.Account;
import com.pinkieyun.fitnesscenter.entity.dto.AccountDTO;
import com.pinkieyun.fitnesscenter.entity.dto.AccountFormDTO;
import com.pinkieyun.fitnesscenter.repository.AccountRepository;
import com.pinkieyun.fitnesscenter.service.AccountService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final ModelMapper modalMapper;

    private AccountDTO toDTO(Account account) {
        AccountDTO response = AccountDTO.builder()
                .id(account.getId())
                .email(account.getEmail())
                .password(account.getPassword())
                .role(account.getRole())
                .organization(account.getOrganization())
                .active(account.isActive())
                .build();
        return response;
    }

    @Override
    public Optional<Account> findOne(Integer id) {
        return accountRepository.findById(id);
    }

    @Override
    public Optional<AccountDTO> findOneDTO(Integer id) {
        return accountRepository.findById(id).map(this::toDTO);
    }

    @Override
    @Transactional
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Optional<AccountDTO> update(Integer id, AccountFormDTO accountFormDTO) {
        Account account = modalMapper.map(accountFormDTO, Account.class);
        update(id, account);
        return Optional.ofNullable(modalMapper.map(account, AccountDTO.class));
    }

    @Override
    public Account update(Integer id, Account account) {
        return findOne(id).map(acc -> {
            account.setId(id);
            return accountRepository.save(account);
        }).orElseThrow();
    }

    @Override
    public Account changeActive(Integer id) {
        return findOne(id).map(acc -> {
            boolean currentStatus = acc.isActive();
            acc.setActive(!currentStatus);
            return accountRepository.save(acc);            
        }).orElseThrow();
    }

    @Override
    public Optional<Account> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    
}
