package com.pinkieyun.fitnesscenter.entity.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDTO {

    private Integer id;

    private String message;

    private LocalDateTime createdDate;
    
    private PersonDTO from;

}
