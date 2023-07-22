package com.pinkieyun.fitnesscenter.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pinkieyun.fitnesscenter.entity.Equipment;
import com.pinkieyun.fitnesscenter.entity.dto.EquipmentFormDTO;

public interface EquipmentService {

    public Optional<Equipment> findOne(Integer id);

    public Page<Equipment> findAll(Pageable pageable);

    public Optional<Equipment> save(EquipmentFormDTO equipmentFormDTO);

    public Optional<Equipment> update(Integer id, EquipmentFormDTO equipmentFormDTO);

    public Optional<Equipment> changeActive(Integer id);
}
