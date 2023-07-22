package com.pinkieyun.fitnesscenter.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pinkieyun.fitnesscenter.Constants.API;
import com.pinkieyun.fitnesscenter.entity.Equipment;
import com.pinkieyun.fitnesscenter.entity.dto.EquipmentFormDTO;
import com.pinkieyun.fitnesscenter.service.EquipmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(API.BASE_API)
@RequiredArgsConstructor
public class EquipmentController {
    private final EquipmentService equipmentService;

    @GetMapping(API.EQUIPMENTS)
    public ResponseEntity<Page<Equipment>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(equipmentService.findAll(pageable));
    }

    @PostMapping(API.EQUIPMENTS + "/create")
    public ResponseEntity<Optional<Equipment>> create(@RequestBody EquipmentFormDTO equipmentFormDTO) {
        return ResponseEntity.ok().body(equipmentService.save(equipmentFormDTO));
    }

    @PutMapping(API.EQUIPMENTS + "/update/{id}")
    public ResponseEntity<Optional<Equipment>> update(@PathVariable Integer id, @RequestBody EquipmentFormDTO equipmentFormDTO) {
        return ResponseEntity.ok().body(equipmentService.update(id,equipmentFormDTO));
    }

    @DeleteMapping(API.EQUIPMENTS + "/delete/{id}")
    public ResponseEntity<Optional<Equipment>> changeActive(@PathVariable Integer id) {
        return ResponseEntity.ok().body(equipmentService.changeActive(id));
    }


}
