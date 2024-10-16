package com.car.system.controller;

import com.car.system.entity.Car;
import com.car.system.exception.EngineFailureException;
import com.car.system.exception.EngineRunningException;
import com.car.system.exception.EngineStoppedException;
import com.car.system.exception.FuelEmptyException;
import com.car.system.exception.FuelOverflowException;
import com.car.system.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
@AllArgsConstructor
public class CarController {
    private CarService carService;

    @PostMapping("/{id}/start")
    public Car startCar(@PathVariable final Long id) throws EngineFailureException, FuelEmptyException, EngineRunningException {
        return carService.startCar(id);
    }

    @PostMapping("/{id}/stop")
    public Car stopCar(@PathVariable final Long id) throws EngineFailureException, EngineStoppedException {
        return carService.stopCar(id);
    }

    @PostMapping("/{id}/refuel")
    public Car refuelCar(@PathVariable final Long id, @RequestParam final double amount) throws FuelOverflowException {
        return carService.refuelCar(id, amount);
    }

    @GetMapping("/{id}/fuel-level")
    public double getFuelLevel(@PathVariable final Long id) {
        return carService.getFuelLevel(id);
    }
}
