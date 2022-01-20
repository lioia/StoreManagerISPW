package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;


import com.example.shoppingpoint.model.Store;
import com.example.shoppingpoint.model.user.*;

import java.io.IOException;

public class ClientListGraphicController {

    private StoreOwner storeOwner;

    @FXML
    private Label labelStoreName;


    @FXML
    public void goBack(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("store_dashboard.fxml"));
        Parent node = fxmlLoader.load();
        ((Node) event.getSource()).getScene().setRoot(node);
        StoreDashboardGraphicController storeDashboardController = fxmlLoader.getController();
        storeDashboardController.initData(storeOwner);
    }

    @FXML
    public void initData(StoreOwner storeOwner) throws IOException {
        this.storeOwner = storeOwner;

        labelStoreName.setText(storeOwner.getStore().getName() + " - Shopping Point");
    }
}
