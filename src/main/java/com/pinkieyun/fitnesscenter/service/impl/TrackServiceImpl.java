package com.pinkieyun.fitnesscenter.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pinkieyun.fitnesscenter.entity.MemberTrack;
import com.pinkieyun.fitnesscenter.entity.dto.MemberTrackDTO;
import com.pinkieyun.fitnesscenter.entity.dto.MemberTrackFormDTO;
import com.pinkieyun.fitnesscenter.repository.TrackRepository;
import com.pinkieyun.fitnesscenter.service.AccountService;
import com.pinkieyun.fitnesscenter.service.PersonService;
import com.pinkieyun.fitnesscenter.service.TrackService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrackServiceImpl implements TrackService {

    private final AccountService accountService;
    private final TrackRepository trackRepository;
    private final PersonService personService;

    public MemberTrackDTO toDTO(MemberTrack memberTrack) {
        return new MemberTrackDTO(memberTrack.getId(), 
        memberTrack.getCheckInTime(), personService.toDTO(memberTrack.getMember()));
    }

    @Override
    public Page<MemberTrackDTO> findByMember(Pageable pageable) {
        Page<MemberTrack> page = trackRepository.findByMember(accountService.getCurrentPerson().getId(), pageable);
        List<MemberTrackDTO> list = page.getContent().stream().map(t -> toDTO(t)).collect(Collectors.toList());
        return new PageImpl<>(list, pageable, page.getTotalElements());
    }

    @Override
    @Transactional
    public MemberTrack create(MemberTrack memberTrack) {
        return trackRepository.save(memberTrack);
    }

    @Override
    @Transactional
    public Optional<MemberTrackDTO> create(MemberTrackFormDTO memberTrackFormDTO) {
        LocalDateTime now = LocalDateTime.now();
        MemberTrack track = new MemberTrack();
        track.setCheckInTime(now);
        track.setMember(accountService.getCurrentPerson());

        trackRepository.save(track);

        return Optional.of(toDTO(track));
    }
    
}
