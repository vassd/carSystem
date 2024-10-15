package com.car.system.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(OutputCaptureExtension.class)
class DashboardTest {
    private final Car car = new Car();

    @BeforeEach
    void setUp() {
        car.setEngine(new Engine());
        car.setFuelTank(new FuelTank());
        car.setDashboard(new Dashboard());
    }

    @Test
    void testDisplay(final CapturedOutput output) {
        car.getDashboard().displayCarStatus(car);

        final String logs = output.getOut();
        assert logs.contains("---- Car Status ----");
        assert logs.contains("Engine Status: Stopped");
        assert logs.contains("Fuel Level: 50.0 liters");
        assert logs.contains("--------------------");
    }

    @Test
    void testDisplayWithRunningEngine(final CapturedOutput output) {
        car.getEngine().setRunning(true);
        car.getFuelTank().setFuelLevel(45);

        car.getDashboard().displayCarStatus(car);

        final String logs = output.getOut();
        assert logs.contains("---- Car Status ----");
        assert logs.contains("Engine Status: Running");
        assert logs.contains("Fuel Level: 45.0 liters");
        assert logs.contains("--------------------");
    }
}
