package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;

public class RequestGraphicController {
    public void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("store_dashboard.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }
    @FXML
    protected void logout(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("Login.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }
}
