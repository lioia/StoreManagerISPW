package com.example.shoppingpoint;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ShoppingPointApplication extends Application {
    private static ShoppingPointApplication instance;

    public static ShoppingPointApplication getInstance() {
//        Create an instance of this class if it's not already present
        if (instance == null)
            instance = new ShoppingPointApplication();
        return instance;
    }

    public ShoppingPointApplication() {
//        Set the instance to the current one (created by the launch method)
        instance = this;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Shopping Point");
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}