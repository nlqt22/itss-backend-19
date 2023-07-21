package com.pinkieyun.fitnesscenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pinkieyun.fitnesscenter.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
    
}
