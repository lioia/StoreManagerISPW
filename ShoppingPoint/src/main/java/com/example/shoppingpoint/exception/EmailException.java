package com.example.shoppingpoint.exception;

import java.io.Serial;

public class EmailException extends Exception {
    //    Necessario perché Exception estende Throwable che implementa Serializable
    @Serial
    private static final long serialVersionUID = 1L;

    public EmailException(String reason) {
        super(reason);
    }

    public EmailException(Throwable cause) {
        super(cause);
    }

    public EmailException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
