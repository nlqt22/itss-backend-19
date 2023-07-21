package com.pinkieyun.fitnesscenter.entity.dto;

import com.pinkieyun.fitnesscenter.entity.Organization;
import com.pinkieyun.fitnesscenter.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private Integer id;

    private String email;

    private String password;

    private Role role;

    private Organization organization;

    private boolean active;
}
