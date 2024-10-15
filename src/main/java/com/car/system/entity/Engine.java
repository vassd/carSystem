package com.car.system.entity;

import com.car.system.exception.EngineFailureException;
import com.car.system.exception.EngineRunningException;
import com.car.system.exception.EngineStoppedException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "engines")
@Data
public class Engine {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private boolean isRunning = false;

    public void start() throws EngineFailureException, EngineRunningException {
        if (isRunning) {
            throw new EngineRunningException("Engine is already running.");
        }
        if (Math.random() < 0.1) {
            throw new EngineFailureException("Engine failed to start.");
        }
        this.isRunning = true;
    }

    public void stop() throws EngineFailureException, EngineStoppedException {
        if (!isRunning) {
            throw new EngineStoppedException("Engine is already stopped.");
        }
        if (Math.random() < 0.1) {
            throw new EngineFailureException("Engine failed to stop.");
        }
        this.isRunning = false;
    }
}
