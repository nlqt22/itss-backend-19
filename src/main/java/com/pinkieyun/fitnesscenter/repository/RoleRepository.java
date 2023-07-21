package com.pinkieyun.fitnesscenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pinkieyun.fitnesscenter.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    
}
