package com.car.system.entity;

import com.car.system.exception.EngineFailureException;
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

    public void start() throws EngineFailureException {
        if (Math.random() < 0.1) {
            throw new EngineFailureException("Engine failed to start.");
        }
        this.isRunning = true;
    }

    public void stop() throws EngineFailureException {
        if (Math.random() < 0.1) {
            throw new EngineFailureException("Engine failed to stop.");
        }
        this.isRunning = false;
    }
}
