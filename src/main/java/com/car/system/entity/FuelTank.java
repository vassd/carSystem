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
    public static final int MAX_LEVEL = 50;
    public static final int FUEL_FOR_START = 5;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private double fuelLevel = MAX_LEVEL;

    public void consumeFuel() throws FuelEmptyException {
        if (fuelLevel < FUEL_FOR_START) {
            throw new FuelEmptyException("Not enough fuel to start the car.");
        }
        fuelLevel -= FUEL_FOR_START;
    }

    public void refuel(final double amount) throws FuelOverflowException {
        if (fuelLevel + amount > MAX_LEVEL) {
            throw new FuelOverflowException("Too much fuel to refill the car.");
        }
        fuelLevel += amount;
    }
}
