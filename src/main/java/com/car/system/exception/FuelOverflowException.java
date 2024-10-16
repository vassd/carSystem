package com.car.system.exception;

import java.io.Serial;

public class FuelOverflowException extends Exception {
    @Serial
    public static final long serialVersionUID = 4328747;

    public FuelOverflowException(final String message) {
        super(message);
    }
}
