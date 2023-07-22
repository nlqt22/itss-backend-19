package com.pinkieyun.fitnesscenter.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pinkieyun.fitnesscenter.entity.Pack;
import com.pinkieyun.fitnesscenter.entity.dto.PackFormDTO;

public interface PackService {
    
    public Optional<Pack> findOne(Integer id);

    public Page<Pack> findAllActive(Pageable pageable);

    public Optional<Pack> save(PackFormDTO packFormDTO);

    public Optional<Pack> update(Integer id, PackFormDTO packFormDTO);

    public Optional<Pack> changeActive(Integer id);
}
