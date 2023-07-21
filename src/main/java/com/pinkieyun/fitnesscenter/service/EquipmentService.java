package com.pinkieyun.fitnesscenter.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pinkieyun.fitnesscenter.entity.Equipment;

public interface EquipmentService {

    public Optional<Equipment> findOne(Integer id);

    public Page<Equipment> findByOrganizationId(Integer organizationId, Pageable pageable);

    public Equipment save(Equipment equipment);

    public Equipment update(Integer id, Equipment equipment);
}
