package com.car.system.controller;

import com.car.system.entity.Car;
import com.car.system.exception.EngineFailureException;
import com.car.system.exception.FuelEmptyException;
import com.car.system.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @PostMapping("/{id}/start")
    public Car startCar(@PathVariable Long id) throws EngineFailureException, FuelEmptyException {
        return carService.startCar(id);
    }

    @PostMapping("/{id}/stop")
    public Car stopCar(@PathVariable Long id) throws EngineFailureException {
        return carService.stopCar(id);
    }

    @PostMapping("/{id}/refuel")
    public Car refuelCar(@PathVariable Long id, @RequestParam double amount) {
        return null;
    }

    @GetMapping("/{id}/fuel-level")
    public double getFuelLevel(@PathVariable Long id) {
        return 0.0;
    }
}
