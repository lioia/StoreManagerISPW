package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.bean.NewStoreBean;
import com.example.shoppingpoint.controller.NewStoreController;
import com.example.shoppingpoint.model.Store;
import com.example.shoppingpoint.model.user.StoreOwner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;

public class NewStoreGraphicController {

    private StoreOwner storeOwner;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private ComboBox<String> typeComboBoxField;

    @FXML
    protected void registerNewStore(ActionEvent actionEvent) throws Exception {
        String name = nameTextField.getText();
        String address = addressTextField.getText();
        String type = typeComboBoxField.getValue();

        NewStoreBean bean = new NewStoreBean(name, address, type);

        NewStoreController controller = new NewStoreController();
        Store store = controller.register(bean, storeOwner.getUsername());
        storeOwner.setStore(store);
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("store_dashboard.fxml"));
        Parent node = fxmlLoader.load();
        ((Node) actionEvent.getSource()).getScene().setRoot(node);
    }

    public void setStoreOwnerName(StoreOwner storeOwner) {
        this.storeOwner = storeOwner;
    }
}
