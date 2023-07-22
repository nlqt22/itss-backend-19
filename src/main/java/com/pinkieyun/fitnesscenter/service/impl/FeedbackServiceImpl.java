package com.pinkieyun.fitnesscenter.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pinkieyun.fitnesscenter.entity.Feedback;
import com.pinkieyun.fitnesscenter.entity.dto.FeedbackDTO;
import com.pinkieyun.fitnesscenter.entity.dto.FeedbackFormDTO;
import com.pinkieyun.fitnesscenter.repository.FeedbackRepository;
import com.pinkieyun.fitnesscenter.service.AccountService;
import com.pinkieyun.fitnesscenter.service.FeedbackService;
import com.pinkieyun.fitnesscenter.service.PersonService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final AccountService accountService;
    private final PersonService personService;

    public FeedbackDTO toDTO(Feedback feedback) {
        FeedbackDTO feedbackDTO = new FeedbackDTO (
            feedback.getId(), 
            feedback.getMessage(), 
            feedback.getCreatedDate(), 
            personService.toDTO(feedback.getFrom()));

        return feedbackDTO;
    }

    @Override
    public Optional<Feedback> findOne(Integer id) {
        return feedbackRepository.findById(id);
    }

    @Override
    public Optional<FeedbackDTO> findOneDTO(Integer id) {
        return feedbackRepository.findById(id).map(this::toDTO);
    }

    @Override
    public Page<FeedbackDTO> findByOrganization(Pageable pageable) {
        Page<Feedback> page = feedbackRepository.findByOrganizationId(accountService.getCurrentOrganization().getId(), pageable);
        List<FeedbackDTO> list = page.getContent().stream().map(fb -> toDTO(fb)).collect(Collectors.toList());
        
        return new PageImpl<>(list, pageable, page.getTotalElements());
    }

    @Override
    public Feedback create(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public Optional<FeedbackDTO> create(FeedbackFormDTO feedbackFormDTO) {

        LocalDateTime now = LocalDateTime.now();

        Feedback feedback = new Feedback();
        feedback.setMessage(feedbackFormDTO.getMessage());
        feedback.setCreatedDate(now);
        feedback.setFrom(accountService.getCurrentPerson());

        feedbackRepository.save(feedback);
        
        return Optional.of(toDTO(feedback));
    }
    
}
