package com.pinkieyun.fitnesscenter.entity.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonFormDTO {

    private String fullName;

    private String phone;

    private String identityCard;

    private Date dob;
    
}
