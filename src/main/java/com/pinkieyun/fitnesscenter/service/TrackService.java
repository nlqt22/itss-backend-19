package com.pinkieyun.fitnesscenter.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pinkieyun.fitnesscenter.entity.MemberTrack;
import com.pinkieyun.fitnesscenter.entity.dto.MemberTrackDTO;
import com.pinkieyun.fitnesscenter.entity.dto.MemberTrackFormDTO;

public interface TrackService {
    public Page<MemberTrackDTO> findByMember(Pageable pageable);

    public MemberTrack create(MemberTrack memberTrack);

    public Optional<MemberTrackDTO> create(MemberTrackFormDTO memberTrackFormDTO);
}
