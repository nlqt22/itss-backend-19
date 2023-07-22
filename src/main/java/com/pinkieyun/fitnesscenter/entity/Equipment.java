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
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "equipment")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "qty")
    private Integer quantity;

    @Column(name = "import_date")
    private LocalDateTime importDate;

    @Column(name = "latest_updated")
    private LocalDateTime latestUpdated;

    @Column(name = "origin")
    private String origin;
    
    @Column(name = "is_active")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;
}
