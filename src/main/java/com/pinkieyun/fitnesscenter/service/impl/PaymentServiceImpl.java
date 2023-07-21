package com.pinkieyun.fitnesscenter.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pinkieyun.fitnesscenter.entity.Pack;
import com.pinkieyun.fitnesscenter.entity.Payment;
import com.pinkieyun.fitnesscenter.entity.dto.PaymentDTO;
import com.pinkieyun.fitnesscenter.entity.dto.PaymentFormDTO;
import com.pinkieyun.fitnesscenter.repository.PaymentRepository;
import com.pinkieyun.fitnesscenter.service.PackService;
import com.pinkieyun.fitnesscenter.service.PaymentService;
import com.pinkieyun.fitnesscenter.service.PersonService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final PersonService personService;
    private final PackService packService;

    private PaymentDTO toDTO(Payment payment) {
        PaymentDTO paymentDTO = PaymentDTO.builder()
            .id(payment.getId())
            .member(personService.toDTO(payment.getMember()))
            .sale(personService.toDTO(payment.getSale()))
            .name(payment.getName())
            .duration(payment.getDuration())
            .price(payment.getPrice())
            .createdDate(payment.getCreatedDate())
            .expiredDate(payment.getExpiredDate())
            .build();
        return paymentDTO;
        
    }

    @Override
    public Optional<Payment> findOne(Integer id) {
        return paymentRepository.findById(id);
    }

    @Override
    public Page<PaymentDTO> findByOrganizationId(Integer organizationId, Pageable pageable) {
        Page<Payment> page = paymentRepository.findByOrganizationId(organizationId, pageable);
        List<PaymentDTO> list = page.getContent().stream().map(payment -> toDTO(payment)).collect(Collectors.toList());
        return new PageImpl<>(list, pageable, page.getTotalElements());
    }

    @Override
    public Page<PaymentDTO> findByOrganizationIdAndMemberId(Integer organizationId, Integer memberId,
            Pageable pageable) {
        Page<Payment> page = paymentRepository.findByOrganizationIdAndMemberId(organizationId, memberId, pageable);
        List<PaymentDTO> list = page.getContent().stream().map(payment -> toDTO(payment)).collect(Collectors.toList());
        return new PageImpl<>(list, pageable, page.getTotalElements());
    }

    @Override
    @Transactional
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    @Transactional
    public Optional<PaymentDTO> create(PaymentFormDTO paymentFormDTO) {
        System.out.println(paymentFormDTO.getPackId() + "PACKID" + paymentFormDTO.getCreatorId());
        
        Pack pack = packService.findOne(paymentFormDTO.getPackId()).get();
        
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expired = now.plusMonths(pack.getDuration());

        Payment payment = new Payment();
        payment.setActive(true);
        payment.setCreatedDate(now);
        payment.setExpiredDate(expired);
        payment.setName(pack.getName());
        payment.setPrice(pack.getPrice());
        payment.setSale(personService.findOne(paymentFormDTO.getCreatorId()).get());
        payment.setMember(personService.findOne(paymentFormDTO.getMemberId()).get());
        payment.setDuration(pack.getDuration());
        payment.setOrganization(pack.getOrganization());

        payment = paymentRepository.save(payment);
        System.out.println(payment.getId() + "DKPASKDL:ASKL:DKALS:DKALS:K");
        PaymentDTO paymentDTO = toDTO(payment);
        return Optional.of(paymentDTO);
    }

}
