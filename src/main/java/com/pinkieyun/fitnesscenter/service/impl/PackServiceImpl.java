package com.pinkieyun.fitnesscenter.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pinkieyun.fitnesscenter.entity.Pack;
import com.pinkieyun.fitnesscenter.repository.PackRepository;
import com.pinkieyun.fitnesscenter.service.PackService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PackServiceImpl implements PackService {
    private final PackRepository packRepository;

    @Override
    public Optional<Pack> findOne(Integer id) {
        return packRepository.findById(id);
    }

    @Override
    public Page<Pack> findByOrganizationId(Integer organizationId, Pageable pageable) {
        return packRepository.findByOrganizationId(organizationId, pageable);
    }

    @Override
    @Transactional
    public Pack save(Pack pack) {
        return packRepository.save(pack);
    }

    @Override
    public Pack update(Integer id, Pack pack) {
        return findOne(id).map(p -> {
            pack.setId(id);
            return packRepository.save(pack);
        }).orElseThrow();
    }
}
