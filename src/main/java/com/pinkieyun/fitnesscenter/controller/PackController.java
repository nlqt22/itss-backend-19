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
import com.pinkieyun.fitnesscenter.entity.Pack;
import com.pinkieyun.fitnesscenter.entity.dto.PackFormDTO;
import com.pinkieyun.fitnesscenter.service.PackService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(API.BASE_API)
@RequiredArgsConstructor
public class PackController {

    private final PackService packService;

    @GetMapping(API.PACKS + "/{id}")
    public ResponseEntity<Optional<Pack>> findOne(@PathVariable Integer id) {
        return ResponseEntity.ok().body(packService.findOne(id));
    }

    @PostMapping(API.PACKS)
    public ResponseEntity<Optional<Pack>> create(@RequestBody PackFormDTO packFormDTO) {
        return ResponseEntity.ok().body(packService.save(packFormDTO));
    }

    @GetMapping(API.PACKS)
    public ResponseEntity<Page<Pack>> findAllActive(Pageable pageable) {
        return ResponseEntity.ok().body(packService.findAllActive(pageable));
    }

    @PutMapping(API.PACKS + "/{id}")
    public ResponseEntity<Optional<Pack>> update(@PathVariable Integer id, @RequestBody PackFormDTO packFormDTO) {
        return ResponseEntity.ok().body(packService.update(id, packFormDTO));
    }
    
    @DeleteMapping(API.PACKS + "/{id}")
    public ResponseEntity<Optional<Pack>> changeActive(@PathVariable Integer id) {
        return ResponseEntity.ok().body(packService.changeActive(id));
    }
}
