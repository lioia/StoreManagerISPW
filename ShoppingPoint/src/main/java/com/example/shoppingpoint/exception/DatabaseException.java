package com.example.shoppingpoint.exception;

import java.io.Serial;

public class DatabaseException extends Exception {
    //    Necessario perché Exception estende Throwable che implementa Serializable
    @Serial
    private static final long serialVersionUID = 1L;

    public DatabaseException(String data) {
        super("Not found: " + data);
    }

    public DatabaseException(Throwable cause) {
        super(cause);
    }

    public DatabaseException(String data, String reason, Throwable cause) {
        super("Not found: " + data, cause);
    }
}
