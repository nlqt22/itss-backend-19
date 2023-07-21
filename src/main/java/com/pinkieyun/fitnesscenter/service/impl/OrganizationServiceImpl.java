package com.pinkieyun.fitnesscenter.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pinkieyun.fitnesscenter.entity.Organization;
import com.pinkieyun.fitnesscenter.repository.OrganizationRepository;
import com.pinkieyun.fitnesscenter.service.OrganizationService;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public List<Organization> findAll() {
        return organizationRepository.findAll();
    }

    @Override
    public Optional<Organization> findOne(Integer id) {
        return organizationRepository.findById(id);
    }


}
