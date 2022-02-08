package com.example.shoppingpoint.exception;

import java.io.Serial;

public class ImageException extends Exception {
    //    Necessario perché Exception estende Throwable che implementa Serializable
    @Serial
    private static final long serialVersionUID = 1L;

    public ImageException(String reason) {
        super(reason);
    }

    public ImageException(Throwable cause) {
        super(cause);
    }

    public ImageException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
