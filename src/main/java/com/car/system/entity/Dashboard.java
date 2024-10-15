package com.car.system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "dashboards")
@Data
public class Dashboard {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
}
