package com.pinkieyun.fitnesscenter.service.impl;

import com.pinkieyun.fitnesscenter.service.AuthenticationService;
import com.pinkieyun.fitnesscenter.service.PersonService;
import com.pinkieyun.fitnesscenter.repository.AccountRepository;
import com.pinkieyun.fitnesscenter.repository.OrganizationRepository;
import com.pinkieyun.fitnesscenter.repository.RoleRepository;
import com.pinkieyun.fitnesscenter.repository.TokenRepository;

import com.pinkieyun.fitnesscenter.entity.Account;
import com.pinkieyun.fitnesscenter.entity.Person;
import com.pinkieyun.fitnesscenter.entity.Token;

import com.pinkieyun.fitnesscenter.entity.auth.AuthenticationRequest;
import com.pinkieyun.fitnesscenter.entity.auth.AuthenticationResponse;
import com.pinkieyun.fitnesscenter.entity.auth.RegisterRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AccountRepository repository;
    private final TokenRepository tokenRepository;
    private final RoleRepository roleRepository;
    private final OrganizationRepository organizationRepository;
    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImpl jwtService;
    private final PersonService personService;

    @Override
    public AuthenticationResponse register(RegisterRequest request, Integer roleId) {
        Account account = Account.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(roleRepository.findById(roleId).get())
                .organization(organizationRepository.findById(request.getOrganization()).get())
                .active(true)
                .build();

        var savedUser = repository.save(account);

        Person person = new Person();
        person.setFullName(request.getFullName());
        person.setDob(request.getDob());
        person.setIdentityCard(request.getIdentityCard());
        person.setPhone(request.getPhone());
        person.setAccount(account);
    
        personService.save(person);

        var jwtToken = jwtService.generateToken(account);
        var refreshToken = jwtService.generateRefreshToken(account);

        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        var account = repository.findByEmail(request.getEmail())
                .orElseThrow();

        if (!passwordEncoder.matches(request.getPassword(), account.getPassword())) {
            System.out.println(request.getPassword() + account.getPassword());
            throw new BadCredentialsException("Invalid credentials");
        }

        var jwtToken = jwtService.generateToken(account);
        var refreshToken = jwtService.generateRefreshToken(account);

        revokeAllUserTokens(account);
        saveUserToken(account, jwtToken);

        return AuthenticationResponse
                .builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserToken(Account account, String jwtToken) {
        Token token = Token.builder()
                .account(account)
                .token(jwtToken)
                .tokenType("BEARER")
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(Account account) {
        List<Token> validAccountTokens = tokenRepository.findAllValidTokenByAccount(account.getId());
        if (validAccountTokens.isEmpty())
            return;

        validAccountTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });

        tokenRepository.saveAll(validAccountTokens);
    }

    @Override
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        System.out.println("userEmail: " + userEmail);

        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
