package com.car.system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "dashboards")
@Data
public class Dashboard {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    public void displayCarStatus(Car car) {
        System.out.println("---- Car Status ----");
        displayEngineStatus(car);
        displayFuelLevel(car);
        System.out.println("--------------------");
    }

    private void displayEngineStatus(Car car) {
        String status = car.getEngine().isRunning() ? "Running" : "Stopped";
        System.out.println("Engine Status: " + status);
    }

    private void displayFuelLevel(Car car) {
        System.out.println("Fuel Level: " + car.getFuelTank().getFuelLevel() + " liters");
    }
}
