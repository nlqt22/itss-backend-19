package com.pinkieyun.fitnesscenter.service;

import java.io.IOException;

import com.pinkieyun.fitnesscenter.entity.auth.AuthenticationRequest;
import com.pinkieyun.fitnesscenter.entity.auth.AuthenticationResponse;
import com.pinkieyun.fitnesscenter.entity.auth.RegisterRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationService {

    public AuthenticationResponse register(RegisterRequest request, Integer roleId);

    public AuthenticationResponse authenticate(AuthenticationRequest request);

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
