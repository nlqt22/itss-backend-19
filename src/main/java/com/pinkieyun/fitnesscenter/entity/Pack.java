package com.pinkieyun.fitnesscenter.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "package")
public class Pack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private float price;

    @Column(name = "duration")
    private short duration;

    @Column(name = "pt_svc")
    private boolean ptService;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "latest_updated")
    private LocalDateTime latestUpdated;

    @Column(name = "is_active")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;
}
