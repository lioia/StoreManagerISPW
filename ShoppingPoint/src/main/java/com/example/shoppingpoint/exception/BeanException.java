package com.example.shoppingpoint.exception;

import java.io.Serial;

public class BeanException extends Exception {
    //    Necessario perché Exception estende Throwable che implementa Serializable
    @Serial
    private static final long serialVersionUID = 1L;

    public BeanException(String data, String reason) {
        super("Invalid data: " + data + ". Reason: " + reason);
    }

    public BeanException(Throwable cause) {
        super(cause);
    }

    public BeanException(String data, String reason, Throwable cause) {
        super("Invalid data: " + data + ". Reason: " + reason, cause);
    }
}
