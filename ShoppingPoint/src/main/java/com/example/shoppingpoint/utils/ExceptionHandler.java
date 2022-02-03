package com.example.shoppingpoint.utils;

import javafx.scene.control.Alert;

public class ExceptionHandler {
    public static void handleException(String headerText, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(headerText);
        alert.setContentText(message);
        alert.show();
    }
}
