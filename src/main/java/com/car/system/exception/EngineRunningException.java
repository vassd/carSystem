package com.car.system.exception;

import java.io.Serial;

public class EngineRunningException extends Exception {
    @Serial
    public static final long serialVersionUID = 4328744;

    public EngineRunningException(final String message) {
        super(message);
    }
}
