package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.bean.RegisterBean;
import com.example.shoppingpoint.controller.RegisterController;
import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.model.user.Client;
import com.example.shoppingpoint.model.user.StoreOwner;
import com.example.shoppingpoint.model.user.Supplier;
import com.example.shoppingpoint.model.user.*;
import com.example.shoppingpoint.singleton.LoggedInUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;

import java.io.IOException;

public class RegisterGraphicController {

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField verifyPasswordTextField;
    @FXML
    private ComboBox<String> userTypeField;

    @FXML
    protected void goBack(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("login.fxml"));
            ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Load error");
            alert.setContentText("Could not load the next screen. Please try again");
            alert.show();
        }
    }

    @FXML
    protected void register(ActionEvent actionEvent) {
        try {
            String username = usernameTextField.getText();
            String password = passwordTextField.getText();
            String verifyPassword = verifyPasswordTextField.getText();
            String email = emailTextField.getText();
            String type = userTypeField.getValue();
            RegisterBean bean = new RegisterBean(email, username, type, password, verifyPassword);

            RegisterController controller = new RegisterController();
            User user = controller.register(bean);
            LoggedInUser.getInstance().setUser(user);
            if (user instanceof Client) {
                FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("searchstore.fxml"));
                ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
            }
            if (user instanceof StoreOwner) {
                FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("new_store.fxml"));
                Parent node = fxmlLoader.load();
                NewStoreGraphicController newStoreController = fxmlLoader.getController();
                newStoreController.setStoreOwnerName((StoreOwner) user);
                ((Node) actionEvent.getSource()).getScene().setRoot(node);
            }
            if (user instanceof Supplier) {
                FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("request_list.fxml"));
                Parent node = fxmlLoader.load();
                ((Node) actionEvent.getSource()).getScene().setRoot(node);
                RequestListGraphicController requestListGraphicControllerController = fxmlLoader.getController();
                requestListGraphicControllerController.initialize();
            }
        } catch (BeanException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Incorrect data");
            alert.setContentText(e.getMessage());
            alert.show();
        } catch (IOException ioException) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Load error");
            alert.setContentText("Could not load the next screen. Please try again");
            alert.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
