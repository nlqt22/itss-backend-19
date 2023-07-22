package com.pinkieyun.fitnesscenter.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pinkieyun.fitnesscenter.entity.Feedback;
import com.pinkieyun.fitnesscenter.entity.dto.FeedbackDTO;
import com.pinkieyun.fitnesscenter.entity.dto.FeedbackFormDTO;

public interface FeedbackService {
    
    public Optional<Feedback> findOne(Integer id);

    public Optional<FeedbackDTO> findOneDTO(Integer id);

    public Page<FeedbackDTO> findByOrganization(Pageable pageable);

    public Feedback create(Feedback feedback);

    public Optional<FeedbackDTO> create(FeedbackFormDTO feedbackFormDTO);

}
