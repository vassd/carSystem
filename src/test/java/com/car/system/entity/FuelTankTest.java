package com.car.system.entity;

import com.car.system.exception.FuelEmptyException;
import com.car.system.exception.FuelOverflowException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FuelTankTest {
    private final FuelTank fuelTank  = new FuelTank();

    @Test
    public void testConsumeFuel() throws FuelEmptyException {
        fuelTank.consumeFuel();

        assertEquals(fuelTank.getFuelLevel(), 45);
    }

    @Test
    public void testConsumeFuelWithLowFuel() {
        fuelTank.setFuelLevel(0);

        final FuelEmptyException exception = assertThrows(FuelEmptyException.class, fuelTank::consumeFuel);
        assertEquals("Not enough fuel to start the car.", exception.getMessage());
    }

    @Test
    public void testRefuel() throws FuelOverflowException {
        fuelTank.setFuelLevel(0);
        fuelTank.refuel(50);

        assertEquals(fuelTank.getFuelLevel(), 50);
    }

    @Test
    public void testRefuelWithTooMuchFuel() {
        final FuelOverflowException exception = assertThrows(FuelOverflowException.class, () -> fuelTank.refuel(100));
        assertEquals("Too much fuel to refill the car.", exception.getMessage());
    }
}