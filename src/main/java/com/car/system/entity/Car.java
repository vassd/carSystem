package com.car.system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;
import lombok.Data;
import static jakarta.persistence.GenerationType.IDENTITY;


@Entity
@Table(
        name = "cars",
        indexes = @Index(
                name = "idx_car_id", columnList = "id"
        )
)
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne
    private Engine engine;

    @OneToOne
    private FuelTank fuelTank;

    @OneToOne
    private Dashboard dashboard;
}

