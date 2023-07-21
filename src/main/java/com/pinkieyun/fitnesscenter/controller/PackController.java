package com.pinkieyun.fitnesscenter.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pinkieyun.fitnesscenter.Constants.API;
import com.pinkieyun.fitnesscenter.entity.Pack;
import com.pinkieyun.fitnesscenter.service.PackService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(API.BASE_API)
@RequiredArgsConstructor
public class PackController {

    private final PackService packService;

    @PostMapping(API.PACKS + "/{id}")
    public ResponseEntity<Optional<Pack>> findOne(@PathVariable Integer id) {
        return ResponseEntity.ok().body(packService.findOne(id));
    }


    @PostMapping(API.PACKS)
    public ResponseEntity<Page<Pack>> findAllActive(Pageable pageable) {
        return ResponseEntity.ok().body(packService.findAllActive(pageable));
    }
}
