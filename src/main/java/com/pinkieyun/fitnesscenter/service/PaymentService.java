package com.pinkieyun.fitnesscenter.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pinkieyun.fitnesscenter.entity.Payment;
import com.pinkieyun.fitnesscenter.entity.dto.PaymentDTO;
import com.pinkieyun.fitnesscenter.entity.dto.PaymentFormDTO;

public interface PaymentService {
    
    public Optional<Payment> findOne(Integer id);

    public Page<PaymentDTO> findAll(Pageable pageable);

    public Page<PaymentDTO> findYourAll(Pageable pageable);

    public Payment save(Payment payment);

    public Optional<PaymentDTO> create(PaymentFormDTO paymentFormDTO); 

}
