package com.pinkieyun.fitnesscenter.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pinkieyun.fitnesscenter.Constants.API;
import com.pinkieyun.fitnesscenter.entity.dto.MemberTrackDTO;
import com.pinkieyun.fitnesscenter.entity.dto.MemberTrackFormDTO;
import com.pinkieyun.fitnesscenter.service.TrackService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(API.BASE_API)
public class TrackController {
    
    private final TrackService trackService;

    @GetMapping(API.TRACKS)
    public ResponseEntity<Page<MemberTrackDTO>> findByMember(Pageable pageable) {
        return ResponseEntity.ok().body(trackService.findByMember(pageable));
    }

    @PostMapping(API.TRACKS + "/create")
    public ResponseEntity<Optional<MemberTrackDTO>> create(@RequestBody MemberTrackFormDTO memberTrackFormDTO) {
        return ResponseEntity.ok().body(trackService.create(memberTrackFormDTO));
    }
}
