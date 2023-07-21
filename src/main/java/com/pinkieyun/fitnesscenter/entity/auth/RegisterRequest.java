package com.pinkieyun.fitnesscenter.entity.auth;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String email;

    private String password;

    private int organization;
    
    private String fullName;

    private String phone;

    private String identityCard;

    private Date dob;
    
}
