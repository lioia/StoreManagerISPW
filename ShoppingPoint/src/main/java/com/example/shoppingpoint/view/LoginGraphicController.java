package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.bean.UserBean;
import com.example.shoppingpoint.controller.LoginController;
import com.example.shoppingpoint.utils.UserType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginGraphicController {

    LoginController controller;

    public TextField usernameTextField;
    public PasswordField passwordTextField;

    @FXML
    protected void goToRegister(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("register.fxml"));
        ((Node)event.getSource()).getScene().setRoot(fxmlLoader.load());
    }

    @FXML
    protected void login(ActionEvent actionEvent) {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        UserBean bean = new UserBean(username, password);

        controller = new LoginController();
        controller.login(bean);
        System.out.println("Logged in");
    }
}
