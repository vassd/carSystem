package com.car.system.service;

import com.car.system.entity.Car;
import com.car.system.exception.EngineFailureException;
import com.car.system.exception.EngineRunningException;
import com.car.system.exception.EngineStoppedException;
import com.car.system.exception.FuelEmptyException;
import com.car.system.exception.FuelOverflowException;
import com.car.system.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarService {

    private CarRepository carRepository;

    private static final String CAR_NOT_FOUND = "Car not found";

    public Car startCar(final Long carId) throws FuelEmptyException, EngineFailureException, EngineRunningException {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException(CAR_NOT_FOUND));

        car.getEngine().start();
        car.getFuelTank().consumeFuel();

        car = carRepository.save(car);

        car.getDashboard().displayCarStatus(car);

        return car;
    }

    public Car stopCar(final Long carId) throws EngineFailureException, EngineStoppedException {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException(CAR_NOT_FOUND));

        car.getEngine().stop();

        car = carRepository.save(car);

        car.getDashboard().displayCarStatus(car);

        return car;
    }

    public Car refuelCar(final Long carId, final double amount) throws FuelOverflowException {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException(CAR_NOT_FOUND));

        car.getFuelTank().refuel(amount);

        car = carRepository.save(car);

        car.getDashboard().displayCarStatus(car);

        return car;
    }

    public double getFuelLevel(final Long carId) {
        final Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException(CAR_NOT_FOUND));

        car.getDashboard().displayCarStatus(car);

        return car.getFuelTank().getFuelLevel();
    }
}
