package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.bean.LoginBean;
import com.example.shoppingpoint.controller.LoginController;
import com.example.shoppingpoint.model.user.StoreOwner;
import com.example.shoppingpoint.model.user.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginGraphicController {

    private LoginController controller;

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;

    @FXML
    protected void goToRegister(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("register.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }

    @FXML
    protected void login(ActionEvent actionEvent) throws Exception {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        LoginBean bean = new LoginBean(username, password);

        controller = new LoginController();
        User user = controller.login(bean);
        if (user instanceof Client) {
            FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("searchstore.fxml"));
            Parent node = fxmlLoader.load();
            ((Node) actionEvent.getSource()).getScene().setRoot(node);
            SearchStoreGraphicController searchStoreGraphicController = fxmlLoader.getController();
            searchStoreGraphicController.setClient((Client) user);
        }
        if (user instanceof StoreOwner) {
            FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("store_dashboard.fxml"));
            Parent node = fxmlLoader.load();
            ((Node) actionEvent.getSource()).getScene().setRoot(node);
            StoreDashboardGraphicController storeDashboardController = fxmlLoader.getController();
            storeDashboardController.setStoreOwner(user);
            storeDashboardController.initData();
        }
        if (user instanceof Supplier) {
            FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("searchstore.fxml"));
            ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
        }
    }
}
