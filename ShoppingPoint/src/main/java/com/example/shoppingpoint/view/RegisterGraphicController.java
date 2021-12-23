package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.bean.RegisterBean;
import com.example.shoppingpoint.bean.UserBean;
import com.example.shoppingpoint.controller.LoginController;
import com.example.shoppingpoint.controller.RegisterController;
import com.example.shoppingpoint.utils.UserType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;

import java.io.IOException;

public class RegisterGraphicController {

    RegisterController controller;

    public TextField usernameTextField;
    public PasswordField passwordTextField;
    public TextField emailTextField;
    public PasswordField verifyPasswordTextField;
    public ComboBox userTypeField;
    @FXML
    protected void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("Login.fxml"));
        ((Node)event.getSource()).getScene().setRoot(fxmlLoader.load());
    }

    @FXML
    protected void register(ActionEvent actionEvent) {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String verifyPassword = verifyPasswordTextField.getText();
        String email  = emailTextField.getText();
        String type =(String) userTypeField.getValue();
        RegisterBean bean = new RegisterBean(email,username,type, password, verifyPassword);

        controller = new RegisterController();
        controller.register(bean);
        System.out.println("Logged in");
    }
}
