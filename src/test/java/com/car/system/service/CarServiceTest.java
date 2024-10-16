package com.car.system.service;

import com.car.system.entity.Car;
import com.car.system.entity.Dashboard;
import com.car.system.entity.Engine;
import com.car.system.entity.FuelTank;
import com.car.system.exception.*;
import com.car.system.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({
        MockitoExtension.class,
        OutputCaptureExtension.class
})
class CarServiceTest {
    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    private final Car car = new Car();

    @BeforeEach
    void setUp() {
        car.setEngine(new Engine());
        car.setFuelTank(new FuelTank());
        car.setDashboard(new Dashboard());
    }

    @Test
    void testStart(final CapturedOutput output) throws EngineRunningException, EngineFailureException, FuelEmptyException {
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        when(carRepository.save(car)).thenReturn(car);

        carService.startCar(1L);

        assertTrue(car.getEngine().isRunning());
        assertEquals(car.getFuelTank().getFuelLevel(), 45);
        verify(carRepository).findById(1L);
        verify(carRepository, times(1)).save(car);

        final String logs = output.getOut();
        assert logs.contains("---- Car Status ----");
        assert logs.contains("Engine Status: Running");
        assert logs.contains("Fuel Level: 45.0 liters");
        assert logs.contains("--------------------");
    }

    @Test
    void testStartNotFound() {
        when(carRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> carService.startCar(1L));
        assertEquals("Car not found", exception.getMessage(), "Exception message should match.");
        verify(carRepository).findById(1L);
        verify(carRepository, never()).save(car);
    }

    @Test
    void testStop(final CapturedOutput output) throws EngineFailureException, EngineStoppedException {
        car.getEngine().setRunning(true);

        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        when(carRepository.save(car)).thenReturn(car);

        carService.stopCar(1L);

        assertFalse(car.getEngine().isRunning());
        verify(carRepository).findById(1L);
        verify(carRepository, times(1)).save(car);

        final String logs = output.getOut();
        assert logs.contains("---- Car Status ----");
        assert logs.contains("Engine Status: Stopped");
        assert logs.contains("Fuel Level: 50.0 liters");
        assert logs.contains("--------------------");
    }

    @Test
    void testStopNotFound() {
        car.getEngine().setRunning(true);

        when(carRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> carService.stopCar(1L));
        assertEquals("Car not found", exception.getMessage(), "Exception message should match.");
        verify(carRepository).findById(1L);
        verify(carRepository, never()).save(car);
    }

    @Test
    void testRefuel(final CapturedOutput output) throws FuelOverflowException {
        car.getFuelTank().setFuelLevel(0);

        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        when(carRepository.save(car)).thenReturn(car);

        carService.refuelCar(1L, 50);

        assertEquals(car.getFuelTank().getFuelLevel(), 50);
        verify(carRepository).findById(1L);
        verify(carRepository, times(1)).save(car);

        final String logs = output.getOut();
        assert logs.contains("---- Car Status ----");
        assert logs.contains("Engine Status: Stopped");
        assert logs.contains("Fuel Level: 50.0 liters");
        assert logs.contains("--------------------");
    }

    @Test
    void testRefuelNotFound() {
        car.getFuelTank().setFuelLevel(0);

        when(carRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> carService.refuelCar(1L, 50));
        assertEquals("Car not found", exception.getMessage(), "Exception message should match.");
        verify(carRepository).findById(1L);
        verify(carRepository, never()).save(car);
    }

    @Test
    void testFuelLevel(final CapturedOutput output) {
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));

        assertEquals(carService.getFuelLevel(1L), 50);
        verify(carRepository).findById(1L);

        final String logs = output.getOut();
        assert logs.contains("---- Car Status ----");
        assert logs.contains("Engine Status: Stopped");
        assert logs.contains("Fuel Level: 50.0 liters");
        assert logs.contains("--------------------");
    }

    @Test
    void testFuelLevelNotFound() {
        when(carRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> carService.getFuelLevel(1L));
        assertEquals("Car not found", exception.getMessage(), "Exception message should match.");
        verify(carRepository).findById(1L);
    }
}