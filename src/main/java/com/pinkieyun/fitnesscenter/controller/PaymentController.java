package com.pinkieyun.fitnesscenter.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pinkieyun.fitnesscenter.Constants.API;
import com.pinkieyun.fitnesscenter.entity.dto.IdRequestDTO;
import com.pinkieyun.fitnesscenter.entity.dto.PaymentDTO;
import com.pinkieyun.fitnesscenter.entity.dto.PaymentFormDTO;
import com.pinkieyun.fitnesscenter.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(API.BASE_API)
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping(API.PAYMENTS)
    public ResponseEntity<Page<PaymentDTO>> findByOrganizationId(@RequestBody IdRequestDTO idRequestDTO, Pageable pageable) {
        return ResponseEntity.ok().body(paymentService.findByOrganizationId(idRequestDTO.getId(), pageable));
    }

    @PostMapping(API.PAYMENTS + "/create")
    public ResponseEntity<Optional<PaymentDTO>> createPayment(@RequestBody PaymentFormDTO paymentFormDTO) {
        System.out.println("PAYMMENT" + paymentFormDTO.toString());
        return ResponseEntity.ok().body(paymentService.create(paymentFormDTO));
    }
}
