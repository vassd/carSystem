package com.car.system.service;

import com.car.system.entity.Car;
import com.car.system.exception.EngineFailureException;
import com.car.system.exception.FuelEmptyException;
import com.car.system.exception.FuelOverflowException;
import com.car.system.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public Car startCar(Long carId) throws FuelEmptyException, EngineFailureException {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        car.getFuelTank().consumeFuel();
        car.getEngine().start();

        car = carRepository.save(car);

        car.getDashboard().displayCarStatus(car);

        return car;
    }

    public Car stopCar(Long carId) throws EngineFailureException {
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
