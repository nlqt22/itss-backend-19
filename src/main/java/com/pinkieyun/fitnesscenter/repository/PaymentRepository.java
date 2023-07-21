package com.pinkieyun.fitnesscenter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pinkieyun.fitnesscenter.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    
    @Query(value = "SELECT p FROM Payment p WHERE p.organization.id = :organizationId")
    Page<Payment> findByOrganizationId(@Param("organizationId") Integer organizationId, Pageable pageable);

    @Query(value = "SELECT p FROM Payment p WHERE p.organization.id = :organizationId AND p.member.id = :memberId")
    Page<Payment> findByOrganizationIdAndMemberId(@Param("organizationId") Integer organizationId,@Param("memberId") Integer memberId, Pageable pageable);
}
