package com.pinkieyun.fitnesscenter.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pinkieyun.fitnesscenter.Constants.API;
import com.pinkieyun.fitnesscenter.entity.Pack;
import com.pinkieyun.fitnesscenter.entity.dto.IdRequestDTO;
import com.pinkieyun.fitnesscenter.service.PackService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(API.BASE_API)
@RequiredArgsConstructor
public class PackController {

    private final PackService packService;

    @PostMapping(API.PACKS + "/id")
    public ResponseEntity<Optional<Pack>> findOne(@RequestBody IdRequestDTO idRequestDTO) {
        return ResponseEntity.ok().body(packService.findOne(idRequestDTO.getId()));
    }


    @PostMapping(API.PACKS)
    public ResponseEntity<Page<Pack>> findByOrganizationId(@RequestBody IdRequestDTO idRequestDTO, Pageable pageable) {
        return ResponseEntity.ok().body(packService.findByOrganizationId(idRequestDTO.getId(), pageable));
    }
}
