package com.pinkieyun.fitnesscenter.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pinkieyun.fitnesscenter.entity.Pack;
import com.pinkieyun.fitnesscenter.entity.dto.PackFormDTO;
import com.pinkieyun.fitnesscenter.repository.PackRepository;
import com.pinkieyun.fitnesscenter.service.AccountService;
import com.pinkieyun.fitnesscenter.service.PackService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PackServiceImpl implements PackService {
    private final PackRepository packRepository;
    private final AccountService accountService;

    @Override
    public Optional<Pack> findOne(Integer id) {
        return packRepository.findById(id);
    }

    @Override
    public Page<Pack> findAllActive(Pageable pageable) {
        return packRepository.findByOrganizationId(accountService.getCurrentOrganization().getId(), pageable);
    }

    @Override
    @Transactional
    public Optional<Pack> save(PackFormDTO packFormDTO) {
        Pack pack = new Pack();

        pack.setActive(true);
        pack.setName(packFormDTO.getName());
        pack.setDuration(packFormDTO.getDuration());
        pack.setLatestUpdated(LocalDateTime.now());
        pack.setPtService(packFormDTO.isPtService());
        pack.setCreatedDate(LocalDateTime.now());
        pack.setPrice(packFormDTO.getPrice());
        pack.setOrganization(accountService.getCurrentOrganization());

        packRepository.save(pack);
        
        return Optional.of(pack);
    }

    @Override
    public Optional<Pack> update(Integer id, PackFormDTO packFormDTO) {
        return findOne(id).map(p -> {
            p.setPrice(packFormDTO.getPrice());
            p.setDuration(packFormDTO.getDuration());
            p.setName(packFormDTO.getName());
            p.setLatestUpdated(LocalDateTime.now());
            packRepository.save(p);

            return Optional.of(p);
        }).orElseThrow();
    }

    @Override
    public Optional<Pack> changeActive(Integer id) {
        return findOne(id).map(p -> {
            boolean active = p.isActive();
            p.setActive(!active);
            p.setLatestUpdated(LocalDateTime.now());
            
            packRepository.save(p);

            return Optional.of(p);
        }).orElseThrow();
    }
}
