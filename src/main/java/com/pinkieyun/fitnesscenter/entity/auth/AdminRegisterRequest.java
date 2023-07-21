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
public class AdminRegisterRequest {
    private String email;

    private String password;
    
    private String fullName;

    private Integer organizationId;

    private String phone;

    private String identityCard;

    private Date dob;
}
