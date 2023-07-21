package com.pinkieyun.fitnesscenter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pinkieyun.fitnesscenter.entity.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
    
    @Query(value = "SELECT e FROM Equipment e WHERE e.organization.id = :organizationId ORDER BY e.latestUpdated")
    Page<Equipment> findByOrganizationId(@Param("organizationId") Integer organizationId, Pageable pageable);
}
