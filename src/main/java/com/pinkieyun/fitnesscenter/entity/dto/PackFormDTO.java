package com.pinkieyun.fitnesscenter.entity.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackFormDTO {

    private String name;

    private float price;

    private short duration;

    private boolean ptService;
    
}
