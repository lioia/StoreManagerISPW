package com.example.shoppingpoint.exception;

import java.io.Serial;

public class BoundaryException extends Exception {
    //    Necessario perché Exception estende Throwable che implementa Serializable
    @Serial
    private static final long serialVersionUID = 1L;

    public BoundaryException(String reason) {
        super(reason);
    }

    public BoundaryException(Throwable cause) {
        super(cause);
    }

    public BoundaryException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
