package com.pinkieyun.fitnesscenter.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pinkieyun.fitnesscenter.Constants.API;
import com.pinkieyun.fitnesscenter.entity.auth.AuthenticationResponse;
import com.pinkieyun.fitnesscenter.entity.auth.RegisterRequest;
import com.pinkieyun.fitnesscenter.entity.dto.IdRequestDTO;
import com.pinkieyun.fitnesscenter.entity.dto.PersonDTO;
import com.pinkieyun.fitnesscenter.entity.dto.PersonFormDTO;
import com.pinkieyun.fitnesscenter.service.AuthenticationService;
import com.pinkieyun.fitnesscenter.service.PersonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(API.BASE_API)
@RequiredArgsConstructor
public class StaffController {
    
    private final PersonService personService;
    private final AuthenticationService authenticationService;

    // public ResponseEntity<Page<PersonDTO>> findByOrganizationId()

    @GetMapping(API.STAFFS + "/sale")
    public ResponseEntity<Page<PersonDTO>> findByOrganizationIdAndRoleSale(@RequestParam Integer id, Pageable pageable) {
        return ResponseEntity.ok().body(personService.findByOrganizationIdAndRoleSale(id, pageable));
    }

    @PostMapping(API.STAFFS + "/personal-trainer")
    public ResponseEntity<Page<PersonDTO>> findByOrganizationIdAndRolePT(@RequestBody IdRequestDTO idRequestDTO, Pageable pageable) {
        return ResponseEntity.ok().body(personService.findByOrganizationIdAndRolePT(idRequestDTO.getId(), pageable));
    }

    @PutMapping(API.STAFFS + "/{id}")
    public ResponseEntity<Optional<PersonDTO>> update(@PathVariable Integer id, @RequestBody  PersonFormDTO personFormDTO) {
        return ResponseEntity.ok().body(personService.update(id, personFormDTO));
    }

    @PostMapping(API.STAFFS + "/register-sale")
    public ResponseEntity<AuthenticationResponse> registerSale(
            @RequestBody RegisterRequest request) {

        Integer SALE_ROLE_ID = 3;
        return ResponseEntity.ok(authenticationService.register(request, SALE_ROLE_ID));
    }

    @PostMapping(API.STAFFS + "/register-personal-trainer")
    public ResponseEntity<AuthenticationResponse> registerPT(
            @RequestBody RegisterRequest request) {

        Integer PT_ROLE_ID = 4;
        return ResponseEntity.ok(authenticationService.register(request, PT_ROLE_ID));
    }
}
