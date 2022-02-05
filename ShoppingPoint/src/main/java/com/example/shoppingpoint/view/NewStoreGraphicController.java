package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.bean.NewStoreBean;
import com.example.shoppingpoint.controller.NewStoreController;
import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.model.Store;
import com.example.shoppingpoint.model.user.StoreOwner;
import com.example.shoppingpoint.utils.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;

import static com.example.shoppingpoint.utils.ExceptionHandler.BEAN_HEADER_TEXT;
import static com.example.shoppingpoint.utils.ExceptionHandler.CONTROLLER_HEADER_TEXT;

public class NewStoreGraphicController {

    private StoreOwner storeOwner;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private ComboBox<String> typeComboBoxField;

    @FXML
    protected void registerNewStore(ActionEvent actionEvent) throws IOException {
        try {
            String name = nameTextField.getText();
            String address = addressTextField.getText();
            String type = typeComboBoxField.getValue();

            NewStoreBean bean = new NewStoreBean(name, address, type);

            NewStoreController controller = new NewStoreController();
            Store store = controller.register(bean, storeOwner.getUsername());
            storeOwner.setStore(store);
            FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("store_dashboard.fxml"));
            ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
        } catch (BeanException e) {
            ExceptionHandler.handleException(BEAN_HEADER_TEXT, e.getMessage());
        } catch (ControllerException e) {
            ExceptionHandler.handleException(CONTROLLER_HEADER_TEXT, e.getMessage());
        }
    }

    public void setStoreOwnerName(StoreOwner storeOwner) {
        this.storeOwner = storeOwner;
    }
}
