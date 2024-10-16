package com.car.system.exception;

import java.io.Serial;

public class FuelEmptyException extends Exception {
    @Serial
    public static final long serialVersionUID = 4328746;

    public FuelEmptyException(final String message) {
        super(message);
    }
}
