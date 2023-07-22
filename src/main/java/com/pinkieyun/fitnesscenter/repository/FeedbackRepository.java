package com.pinkieyun.fitnesscenter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pinkieyun.fitnesscenter.entity.Feedback;


public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    
    @Query(value = "SELECT f FROM Feedback f WHERE f.from.account.organization.id = :organizationId")
    Page<Feedback> findByOrganizationId(@Param("organizationId") Integer organizationId, Pageable pageable);

    @Query(value = "SELECT f FROM Feedback f WHERE f.from.id = :memberId")
    Page<Feedback> findByMember(@Param("memberId") Integer memberId, Pageable pageable);
}
