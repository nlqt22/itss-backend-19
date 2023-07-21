package com.pinkieyun.fitnesscenter.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentFormDTO {

    private Integer memberId;
    private Integer creatorId;
    private Integer packId;

}
