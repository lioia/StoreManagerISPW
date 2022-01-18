package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.bean.StoreBean;
import com.example.shoppingpoint.controller.StoreDashboardController;
import com.example.shoppingpoint.utils.UserType;
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

    private String storeName;
    private Store store;
    private StoreDashboardController controller;
    private User storeOwner;

    @FXML
    private FlowPane productsPane;
    public StoreDashboardGraphicController() {controller = new StoreDashboardController();}

    public void initData() throws Exception{
        setStore(storeOwner);
        labelStoreName.setText(store.getName()+" - Shopping Point");
        createProductsView(store);

    }
    public Store setStore  (User storeOwner)throws Exception{
        this.store = StoreDAO.getStoreByStoreOwnerUsername(storeOwner.getUsername());
        return store;
    }


    @FXML
    public void goToClientList(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("client_list.fxml"));
        Parent node = fxmlLoader.load();
        ((Node) event.getSource()).getScene().setRoot(node);
        ClientListGraphicController clientListGraphicController= fxmlLoader.getController();
        clientListGraphicController.initData(storeOwner,store);
    }

    public void goToRequest(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("request.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }
    //TODO devo farlo diventare tipo StoreOwner prima?
    public void setStoreOwner(User storeOwnerName) {
        this.storeOwner = storeOwnerName;
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
            //((Label) pane.lookup("#status")).setText(getStatusNameFromType(product.getStatus()));
            ((TextField) pane.lookup("#description")).setText(product.getDescription());
            ((Button) pane.lookup("#editButton")).setOnAction((ActionEvent event) -> {
//            TODO
            });
//            Add product to the view
            productsPane.getChildren().add(pane);
        }
    }
}
