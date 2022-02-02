package com.example.shoppingpoint.exception;

import java.io.Serial;

public class InvalidInputException extends Exception {
    //    Necessario perché Exception estende Throwable che implementa Serializable
    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidInputException(String data) {
        super("Invalid input: " + data);
    }

    public InvalidInputException(Throwable cause) {
        super(cause);
    }

    public InvalidInputException(String data, String reason, Throwable cause) {
        super("Invalid Input: " + data, cause);
    }
}
