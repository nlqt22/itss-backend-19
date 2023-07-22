package com.pinkieyun.fitnesscenter.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pinkieyun.fitnesscenter.Constants.API;
import com.pinkieyun.fitnesscenter.entity.auth.AuthenticationResponse;
import com.pinkieyun.fitnesscenter.entity.auth.RegisterRequest;
import com.pinkieyun.fitnesscenter.entity.dto.PersonDTO;
import com.pinkieyun.fitnesscenter.entity.dto.PersonFormDTO;
import com.pinkieyun.fitnesscenter.service.AuthenticationService;
import com.pinkieyun.fitnesscenter.service.PersonService;

import lombok.RequiredArgsConstructor;;

@RestController
@RequestMapping(API.BASE_API)
@RequiredArgsConstructor
public class MemberController {
    
    private final PersonService personService;
    private final AuthenticationService authenticationService;
    
    @GetMapping(API.MEMBERS)
    public ResponseEntity<Page<PersonDTO>> findAllMemberActive(Pageable pageable) {
        return ResponseEntity.ok().body(personService.findAllMemberActive(pageable));
    }

    @GetMapping(API.MEMBERS + "/{id}")
    public ResponseEntity<Optional<PersonDTO>> findOne(@PathVariable Integer id) {
        return ResponseEntity.ok().body(personService.findOneDTO(id));
    }

    @PutMapping(API.MEMBERS + "/{id}")
    public ResponseEntity<Optional<PersonDTO>> update(@PathVariable Integer id, @RequestBody PersonFormDTO personFormDTO) {
        return ResponseEntity.ok().body(personService.update(id, personFormDTO));
    }

    @PostMapping(API.MEMBERS + "/register")
    public ResponseEntity<AuthenticationResponse> registerMember(
            @RequestBody RegisterRequest request) {

        Integer MEMBER_ROLE_ID = 2;
        return ResponseEntity.ok(authenticationService.register(request, MEMBER_ROLE_ID));
    }

    @DeleteMapping(API.MEMBERS + "/{id}")
    public ResponseEntity<Optional<PersonDTO>> delete(@PathVariable Integer id) {
        return ResponseEntity.ok().body(personService.changeActive(id));
    }

}
