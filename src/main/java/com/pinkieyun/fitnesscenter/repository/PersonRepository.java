package com.pinkieyun.fitnesscenter.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pinkieyun.fitnesscenter.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>, JpaSpecificationExecutor<Person> {
    
    @Query("SELECT p FROM Person p WHERE p.account.organization.id = :organizationId AND p.account.role.name = 'SALE_STAFF' ORDER BY p.id")
    Page<Person> findByRoleSale(@Param("organizationId") Integer organizationId, Pageable pageable);

    @Query("SELECT p FROM Person p WHERE p.account.organization.id = :organizationId AND p.account.role.name = 'PT_STAFF' ORDER BY p.id")
    Page<Person> findByRolePT(@Param("organizationId") Integer organizationId, Pageable pageable);

    @Query("SELECT p FROM Person p WHERE p.account.organization.id = :organizationId AND p.account.role.name = 'MEMBER' ORDER BY p.id")
    Page<Person> findByRoleMember(@Param("organizationId") Integer organizationId, Pageable pageable);

    @Query("SELECT p FROM Person p WHERE p.account.email = :email AND p.account.organization.id = :organizationId")
    Optional<Person> findByAccountEmail(@Param("organizationId")Integer organizationId, @Param("email") String email);
}
