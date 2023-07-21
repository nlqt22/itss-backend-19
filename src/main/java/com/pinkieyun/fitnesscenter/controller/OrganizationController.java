package com.pinkieyun.fitnesscenter.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pinkieyun.fitnesscenter.Constants.API;
import com.pinkieyun.fitnesscenter.entity.Organization;
import com.pinkieyun.fitnesscenter.service.OrganizationService;

@RestController
@RequestMapping(API.BASE_API)
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping(API.ORGANIZATIONS)
    public ResponseEntity<List<Organization>>findAll() {
        return ResponseEntity.ok().body(organizationService.findAll());
    }
}
