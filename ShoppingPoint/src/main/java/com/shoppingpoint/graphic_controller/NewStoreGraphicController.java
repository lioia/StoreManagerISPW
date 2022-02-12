package com.shoppingpoint.graphic_controller;

import com.shoppingpoint.ShoppingPointApplication;
import com.shoppingpoint.bean.NewStoreBean;
import com.shoppingpoint.bean.RegisterBean;
import com.shoppingpoint.controller.NewStoreController;
import com.shoppingpoint.controller.RegisterController;
import com.shoppingpoint.exception.BeanException;
import com.shoppingpoint.exception.ControllerException;
import com.shoppingpoint.model.Store;
import com.shoppingpoint.model.user.StoreOwner;
import com.shoppingpoint.singleton.LoggedInUser;
import com.shoppingpoint.utils.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class NewStoreGraphicController {

    private RegisterBean registerBean;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private ComboBox<String> typeComboBoxField;

    @FXML
    public void initialize(RegisterBean bean) {
        this.registerBean = bean;
    }

    @FXML
    protected void registerNewStore(ActionEvent actionEvent) throws IOException {
        try {
            String name = nameTextField.getText();
            String address = addressTextField.getText();
            String type = typeComboBoxField.getValue();

            NewStoreBean bean = new NewStoreBean(name, address, type);

            RegisterController registerController = new RegisterController();
            StoreOwner user = (StoreOwner) registerController.register(registerBean);
            NewStoreController newStoreController = new NewStoreController();

            Store store = newStoreController.register(bean, user.getUsername());
            user.setStore(store);
            LoggedInUser.getInstance().setUser(user);
            FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("store_dashboard.fxml"));
            ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
        } catch (BeanException e) {
            ExceptionHandler.handleException(ExceptionHandler.BEAN_HEADER_TEXT, e.getMessage());
        } catch (ControllerException e) {
            ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT, e.getMessage());
        }
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("register.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }
}
