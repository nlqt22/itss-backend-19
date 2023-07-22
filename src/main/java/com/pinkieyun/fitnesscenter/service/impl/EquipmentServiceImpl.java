package com.pinkieyun.fitnesscenter.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pinkieyun.fitnesscenter.entity.Equipment;
import com.pinkieyun.fitnesscenter.entity.dto.EquipmentFormDTO;
import com.pinkieyun.fitnesscenter.exception.BusinessError;
import com.pinkieyun.fitnesscenter.exception.BusinessErrorException;
import com.pinkieyun.fitnesscenter.exception.NotFoundException;
import com.pinkieyun.fitnesscenter.repository.EquipmentRepository;
import com.pinkieyun.fitnesscenter.service.AccountService;
import com.pinkieyun.fitnesscenter.service.EquipmentService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final AccountService accountService;

    @Override
    public Optional<Equipment> findOne(Integer id) {
        return equipmentRepository.findById(id);
    }

    @Override
    public Page<Equipment> findAll(Pageable pageable) {
        return equipmentRepository.findByOrganizationId(accountService.getCurrentOrganization().getId(), pageable);
    }

    @Override
    @Transactional
    public Optional<Equipment> save(EquipmentFormDTO equipmentFormDTO) {
        
        LocalDateTime now = LocalDateTime.now();

        Equipment equipment = new Equipment();
        equipment.setActive(true);
        equipment.setImportDate(equipmentFormDTO.getImportDate());
        equipment.setName(equipmentFormDTO.getName());
        equipment.setLatestUpdated(now);
        equipment.setQuantity(equipment.getQuantity());
        equipment.setOrganization(accountService.getCurrentOrganization());

        equipmentRepository.save(equipment);

        return Optional.of(equipment);
    }

    @Override
    @Transactional
    public Optional<Equipment> update(Integer id, EquipmentFormDTO equipmentFormDTO) throws NotFoundException {
        return findOne(id).map(e -> {
            LocalDateTime now = LocalDateTime.now();
            e.setImportDate(equipmentFormDTO.getImportDate());
            e.setName(equipmentFormDTO.getName());
            e.setOrigin(equipmentFormDTO.getOrigin());
            e.setQuantity(equipmentFormDTO.getQuantity());
            e.setLatestUpdated(now);

            equipmentRepository.save(e);

            return Optional.of(e);

        }).orElseThrow(
                () -> new BusinessErrorException().businessError(
                        new BusinessError().errorCode("error.equipment.idIsNotExists")
                                .params(Collections.singletonList(id))));
    }

    @Override
    @Transactional
    public Optional<Equipment> changeActive(Integer id)  throws NotFoundException {
        return findOne(id).map(e -> {
            LocalDateTime now = LocalDateTime.now();
            boolean active = e.isActive();
            e.setActive(!active);
            e.setLatestUpdated(now);

            equipmentRepository.save(e);

            return Optional.of(e);

        }).orElseThrow(
                () -> new BusinessErrorException().businessError(
                        new BusinessError().errorCode("error.equipment.idIsNotExists")
                                .params(Collections.singletonList(id))));
    }
}
