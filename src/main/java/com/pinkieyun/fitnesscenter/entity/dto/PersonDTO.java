package com.pinkieyun.fitnesscenter.entity.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    private Integer id;

    private String fullName;

    private String phone;

    private String identityCard;

    private Date dob;

    private AccountDTO account;
}
