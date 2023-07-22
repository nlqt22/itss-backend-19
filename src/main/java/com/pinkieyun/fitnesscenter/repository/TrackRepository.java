package com.pinkieyun.fitnesscenter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pinkieyun.fitnesscenter.entity.MemberTrack;

public interface TrackRepository extends JpaRepository<MemberTrack, Integer> {

    @Query(value = "SELECT t FROM MemberTrack t WHERE t.member.id = :memberId")
    Page<MemberTrack> findByMember(@Param("memberId") Integer memberId, Pageable pageable);

    
}
