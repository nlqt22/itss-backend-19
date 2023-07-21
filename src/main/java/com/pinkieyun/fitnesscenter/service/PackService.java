package com.pinkieyun.fitnesscenter.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pinkieyun.fitnesscenter.entity.Pack;

public interface PackService {
    
    public Optional<Pack> findOne(Integer id);

    public Page<Pack> findByOrganizationId(Integer organizationId, Pageable pageable);

    public Pack save(Pack pack);

    public Pack update(Integer id, Pack pack);
}
