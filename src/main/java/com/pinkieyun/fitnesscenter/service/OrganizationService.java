package com.pinkieyun.fitnesscenter.service;

import java.util.List;
import java.util.Optional;

import com.pinkieyun.fitnesscenter.entity.Organization;

public interface OrganizationService {
    
    public List<Organization> findAll();

    public Optional<Organization> findOne(Integer id);
}
