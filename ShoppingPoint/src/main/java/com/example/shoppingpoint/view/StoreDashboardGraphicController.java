package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.controller.StoreDashboardController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import javafx.scene.control.TextField;


import com.example.shoppingpoint.model.user.*;
import com.example.shoppingpoint.model.Store;
import com.example.shoppingpoint.dao.StoreDAO;
import com.example.shoppingpoint.view.ClientListGraphicController;

import java.io.IOException;
import java.util.List;

public class StoreDashboardGraphicController {

    @FXML
    private Label labelStoreName;

    private final StoreDashboardController controller;
    private StoreOwner storeOwner;

    @FXML
    private FlowPane productsPane;

    public StoreDashboardGraphicController() {
        controller = new StoreDashboardController();
    }

    public void initData(StoreOwner owner) throws Exception {
        setStoreOwner(owner);
        labelStoreName.setText(storeOwner.getStore().getName() + " - Shopping Point");
        createProductsView(storeOwner.getStore());
    }

    @FXML
    public void goToClientList(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("client_list.fxml"));
        Parent node = fxmlLoader.load();
        ((Node) event.getSource()).getScene().setRoot(node);
        ClientListGraphicController clientListGraphicController = fxmlLoader.getController();
        clientListGraphicController.initData(storeOwner);
    }

    public void goToRequest(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("request.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }

    private void setStoreOwner(StoreOwner storeOwnerName) throws Exception {
        this.storeOwner = storeOwnerName;
        if (storeOwner.getStore() == null) {
            Store store = controller.getStoreFromStoreOwnerName(storeOwner.getUsername());
            storeOwner.setStore(store);
        }
    }

    private void createProductsView(Store store) throws Exception {
        productsPane.getChildren().clear();
        List<GenericProduct> products = controller.getProductsFromStore(store);

        for (GenericProduct product : products) {
            FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("reusable/store_dashboard_product_pane.fxml"));
            AnchorPane pane = fxmlLoader.load();
//            Set product data in the View
            ((TextField) pane.lookup("#name")).setText(product.getName());
            String formattedPrice = String.format("%.02f€", product.getPrice()); // Price with 2 decimal points
            ((TextField) pane.lookup("#price")).setText(formattedPrice);
            String formattedDiscountedPrice = String.format("%.02f€", product.getDiscountedPrice()); // Price with 2 decimal points
            ((TextField) pane.lookup("#discountedPrice")).setText(formattedDiscountedPrice);
            ((Label) pane.lookup("#status")).setText(product.getStatus());
            ((TextField) pane.lookup("#description")).setText(product.getDescription());
            ((Button) pane.lookup("#editButton")).setOnAction((ActionEvent event) -> {
//            TODO
            });
//            Add product to the view
            productsPane.getChildren().add(pane);
        }
    }
}
