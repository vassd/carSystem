package com.car.system.entity;

import com.car.system.exception.FuelEmptyException;
import com.car.system.exception.FuelOverflowException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "fueltanks")
@Data
public class FuelTank {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private double fuelLevel = 50;

    public void consumeFuel() throws FuelEmptyException {
        if (fuelLevel < 5) {
            throw new FuelEmptyException("Not enough fuel to start the car.");
        }
        fuelLevel -= 5;
    }

    public void refuel(final double amount) throws FuelOverflowException {
        if (fuelLevel + amount > 50) {
            throw new FuelOverflowException("Too much fuel to refill the car.");
        }
        fuelLevel += amount;
    }
}
