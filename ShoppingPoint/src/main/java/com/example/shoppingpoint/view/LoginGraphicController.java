package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.bean.LoginBean;
import com.example.shoppingpoint.controller.LoginController;
import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.model.user.Client;
import com.example.shoppingpoint.model.user.StoreOwner;
import com.example.shoppingpoint.model.user.Supplier;
import com.example.shoppingpoint.model.user.User;
import com.example.shoppingpoint.singleton.LoggedInUser;
import com.example.shoppingpoint.utils.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

import static com.example.shoppingpoint.utils.ExceptionHandler.BEAN_HEADER_TEXT;
import static com.example.shoppingpoint.utils.ExceptionHandler.CONTROLLER_HEADER_TEXT;

public class LoginGraphicController {

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
    protected void login(ActionEvent actionEvent) throws IOException {
        try {
            String username = usernameTextField.getText();
            String password = passwordTextField.getText();
            LoginBean bean = new LoginBean(username, password);
            LoginController controller = new LoginController();
            User user = controller.login(bean);
            LoggedInUser.getInstance().setUser(user);
            if (user instanceof Client) {
                FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("searchstore.fxml"));
                ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
            }
            if (user instanceof StoreOwner) {
                FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("store_dashboard.fxml"));
                ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
            }
            if (user instanceof Supplier) {
                FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("request_list.fxml"));
                ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
            }
        } catch (BeanException e) {
            ExceptionHandler.handleException(BEAN_HEADER_TEXT, e.getMessage());
        } catch (ControllerException e) {
            ExceptionHandler.handleException(CONTROLLER_HEADER_TEXT, e.getMessage());
        }
    }
}
