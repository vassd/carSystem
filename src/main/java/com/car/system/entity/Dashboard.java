package com.car.system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "dashboards")
@Data
public class Dashboard {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private static final Logger LOG = LoggerFactory.getLogger(Dashboard.class);

    public void displayCarStatus(final Car car) {
        LOG.info("---- Car Status ----");
        displayEngineStatus(car);
        displayFuelLevel(car);
        LOG.info("--------------------");
    }

    private void displayEngineStatus(final Car car) {
        final String status = car.getEngine().isRunning() ? "Running" : "Stopped";
        LOG.info("Engine Status: {}", status);
    }

    private void displayFuelLevel(final Car car) {
        LOG.info("Fuel Level: {} liters", car.getFuelTank().getFuelLevel());
    }
}
