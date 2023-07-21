package com.pinkieyun.fitnesscenter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pinkieyun.fitnesscenter.entity.Pack;

public interface PackRepository extends JpaRepository<Pack, Integer> {
    
    @Query(value = "SELECT p FROM Pack p WHERE p.organization.id = :organizationId ORDER BY p.latestUpdated")
    Page<Pack> findByOrganizationId(@Param("organizationId") Integer organizationId, Pageable pageable);
}
