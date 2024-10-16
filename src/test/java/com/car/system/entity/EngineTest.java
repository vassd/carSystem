package com.car.system.entity;

import com.car.system.exception.EngineFailureException;
import com.car.system.exception.EngineRunningException;
import com.car.system.exception.EngineStoppedException;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class EngineTest {
    private final Engine engine  = new Engine();

    @Test
    void testStartEngineSuccess() throws EngineRunningException, EngineFailureException {
        engine.start();

        assertTrue(engine.isRunning(), "Engine should be running after starting successfully.");
    }

    @Test
    void testStartEngineWhenAlreadyRunning() {
        engine.setRunning(true);

        final EngineRunningException exception = assertThrows(EngineRunningException.class, engine::start);
        assertEquals("Engine is already running.", exception.getMessage());
    }

    @Test
    void testStartEngineFailure() {
        engine.setRunning(false);

        try {
            engine.start();
        } catch (EngineFailureException e) {
            assertFalse(engine.isRunning(), "Engine should not be running after a failure to start.");
        } catch (EngineRunningException e) {
            fail("Unexpected EngineRunningException was thrown.");
        }
    }

    @Test
    void testStopEngineSuccess() throws EngineFailureException, EngineStoppedException {
        engine.setRunning(true);

        engine.stop();

        assertFalse(engine.isRunning(), "Engine should be stopped after stopping successfully.");
    }

    @Test
    void testStopEngineWhenAlreadyStopped() {
        engine.setRunning(false);

        final EngineStoppedException exception = assertThrows(EngineStoppedException.class, engine::stop);
        assertEquals("Engine is already stopped.", exception.getMessage());
    }

    @RepeatedTest(10)
    void testStopEngineFailure() {
        engine.setRunning(true);

        try {
            engine.stop();
        } catch (EngineFailureException e) {
            assertTrue(engine.isRunning(), "Engine should still be running after a failure to stop.");
        } catch (EngineStoppedException e) {
            fail("Unexpected EngineStoppedException was thrown.");
        }
    }
}