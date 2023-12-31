package com.pinkieyun.fitnesscenter.entity.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentFormDTO {

    private String name;

    private Integer quantity;

    private LocalDateTime importDate;

    private String origin;

}
