package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.bean.NewStoreBean;
import com.example.shoppingpoint.controller.NewStoreController;
import com.example.shoppingpoint.model.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;

import java.io.IOException;
public class NewStoreGraphicController {

    NewStoreController controller;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private ComboBox typeComboBoxField;

    @FXML
    protected void registerNewStore(ActionEvent actionEvent) throws Exception {
        String name = nameTextField.getText();
        String address = addressTextField.getText();
        String type = (String) typeComboBoxField.getValue();

        NewStoreBean bean = new NewStoreBean(name, address, type);

        controller = new NewStoreController();
       // TODO passare il nome del proprietario
        String storeOwner="storeowner2";
        Store store = controller.register(bean,storeOwner);
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("store_dashboard.fxml"));
        ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
    }


}
