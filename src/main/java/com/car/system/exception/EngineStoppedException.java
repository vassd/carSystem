package com.car.system.exception;

import java.io.Serial;

public class EngineStoppedException extends Exception {
    @Serial
    public static final long serialVersionUID = 4328745;

    public EngineStoppedException(final String message) {
        super(message);
    }
}
