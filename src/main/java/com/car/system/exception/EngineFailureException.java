package com.car.system.exception;

import java.io.Serial;

public class EngineFailureException extends Exception {
    @Serial
    public static final long serialVersionUID = 4328743;

    public EngineFailureException(final String message) {
        super(message);
    }
}
