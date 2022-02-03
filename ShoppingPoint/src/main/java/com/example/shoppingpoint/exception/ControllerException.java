package com.example.shoppingpoint.exception;

import java.io.Serial;

public class ControllerException extends Exception {
    //    Necessario perché Exception estende Throwable che implementa Serializable
    @Serial
    private static final long serialVersionUID = 1L;

    public ControllerException(String reason) {
        super(reason);
    }

    public ControllerException(Throwable cause) {
        super(cause);
    }

    public ControllerException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
