package com.car.system.service;

import com.car.system.entity.Car;
import com.car.system.exception.*;
import com.car.system.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public Car startCar(Long carId) throws FuelEmptyException, EngineFailureException, EngineRunningException {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        car.getEngine().start();
        car.getFuelTank().consumeFuel();

        car = carRepository.save(car);

        car.getDashboard().displayCarStatus(car);

        return car;
    }

    public Car stopCar(Long carId) throws EngineFailureException, EngineStoppedException {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        car.getEngine().stop();

        car = carRepository.save(car);

        car.getDashboard().displayCarStatus(car);

        return car;
    }

    public Car refuelCar(Long carId, double amount) throws FuelOverflowException {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        car.getFuelTank().refuel(amount);

        car = carRepository.save(car);

        car.getDashboard().displayCarStatus(car);

        return car;
    }

    public double getFuelLevel(Long carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        car.getDashboard().displayCarStatus(car);

        return car.getFuelTank().getFuelLevel();
    }
}
