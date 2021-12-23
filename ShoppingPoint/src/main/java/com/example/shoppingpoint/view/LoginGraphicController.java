package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.bean.LoginBean;
import com.example.shoppingpoint.controller.LoginController;
import com.example.shoppingpoint.model.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    protected void login(ActionEvent actionEvent) throws Exception {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        LoginBean bean = new LoginBean(username, password);

        controller = new LoginController();
        User user = controller.login(bean);
        System.out.println("Logged in as " + user.getUsername());
    }
}
