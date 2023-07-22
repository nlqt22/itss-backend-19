package com.pinkieyun.fitnesscenter.entity.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberTrackDTO {

    private Integer id;

    private LocalDateTime checkInTime;
    
    private PersonDTO member;
}
