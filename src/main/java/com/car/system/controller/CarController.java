package com.car.system.controller;

import com.car.system.entity.Car;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class CarController {
    @PostMapping("/{id}/start")
    public Car startCar(@PathVariable Long id)  {
        return null;
    }

    @PostMapping("/{id}/stop")
    public Car stopCar(@PathVariable Long id)  {
        return null;
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
