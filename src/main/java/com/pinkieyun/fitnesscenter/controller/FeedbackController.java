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
import com.pinkieyun.fitnesscenter.entity.dto.FeedbackDTO;
import com.pinkieyun.fitnesscenter.entity.dto.FeedbackFormDTO;
import com.pinkieyun.fitnesscenter.service.FeedbackService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(API.BASE_API)
public class FeedbackController {
    private final FeedbackService feedbackService;

    @GetMapping(API.FEEDBACK)
    public ResponseEntity<Page<FeedbackDTO>> findAllByOrganization(Pageable pageable) {
        return ResponseEntity.ok().body(feedbackService.findByOrganization(pageable));
    }

    @PostMapping(API.FEEDBACK + "/create")
    public ResponseEntity<Optional<FeedbackDTO>> create(@RequestBody FeedbackFormDTO feedbackFormDTO) {
        return ResponseEntity.ok().body(feedbackService.create(feedbackFormDTO));
    }
}
