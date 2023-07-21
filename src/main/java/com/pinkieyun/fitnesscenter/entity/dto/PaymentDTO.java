package com.pinkieyun.fitnesscenter.entity.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDTO {

    private Integer id;

    private PersonDTO member;

    private PersonDTO sale;

    private String name; // packname

    private short duration;

    private float price;

    private LocalDateTime createdDate;

    private LocalDateTime expiredDate;

    private boolean isActive;
}
