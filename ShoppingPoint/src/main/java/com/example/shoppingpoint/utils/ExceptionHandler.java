package com.example.shoppingpoint.utils;

import javafx.scene.control.Alert;

public class ExceptionHandler {
    private ExceptionHandler() {
        throw new IllegalStateException();
    }

    public static final String CONTROLLER_HEADER_TEXT = "Controller Error";
    public static final String BEAN_HEADER_TEXT = "Incorrect Data";

    public static void handleException(String headerText, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(headerText);
        alert.setContentText(message);
        alert.show();
    }
}
