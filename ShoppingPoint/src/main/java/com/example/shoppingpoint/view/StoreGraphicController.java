package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.bean.StoreBean;
import com.example.shoppingpoint.controller.StoreController;
import com.example.shoppingpoint.model.Store;
import com.example.shoppingpoint.model.user.Client;
import com.example.shoppingpoint.utils.StatusType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.List;

public class StoreGraphicController {
    private Client client;
    private Store store;
    private final StoreController controller;

    @FXML
    private Label storeNameText;
    @FXML
    private Label loyaltyCardName;
    @FXML
    private FlowPane productsPane;
    @FXML
    private TextField searchTextField;

    public StoreGraphicController() {
        controller = new StoreController();
    }

    public void initData() throws Exception {
        storeNameText.setText(store.getName() + " - Shopping Point");
        loyaltyCardName.setText(String.format("Every %d points equals 1 euro in discount", store.getPointsInEuro()));

        createProductsView(new StoreBean(store.getName()));
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("searchstore.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }

    @FXML
    public void search() throws Exception {
//        TODO filtro per tipo
        String searchQuery = searchTextField.getText();

        createProductsView(new StoreBean(store.getName(), searchQuery));
    }

    private void createProductsView(StoreBean bean) throws Exception {
        productsPane.getChildren().clear();
        List<GenericProduct> products = controller.getProductsFromStore(bean);

        for (GenericProduct product : products) {
            FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("reusable/store_product_pane.fxml"));
            AnchorPane pane = fxmlLoader.load();
//            Set product data in the View
            ((Label) pane.lookup("#name")).setText(product.getName());
            String formattedPrice = String.format("%.02f€", product.getPrice()); // Price with 2 decimal points
            ((Label) pane.lookup("#price")).setText(formattedPrice);
            String formattedDiscountedPrice = String.format("%.02f€", product.getDiscountedPrice()); // Price with 2 decimal points
            ((Label) pane.lookup("#discountedPrice")).setText(formattedDiscountedPrice);
            ((Label) pane.lookup("#status")).setText(getStatusNameFromType(product.getStatus()));
            ((Label) pane.lookup("#description")).setText(product.getDescription());
            ((Button) pane.lookup("#buyButton")).setOnAction((ActionEvent event) -> {
//            TODO button click
            });
//            Add product to the view
            productsPane.getChildren().add(pane);
        }
    }

    private String getStatusNameFromType(StatusType type) {
        switch (type) {
            case NEW -> {
                return "New";
            }
            case USED -> {
                return "Used";
            }
            case USEDLIKENEW -> {
                return "Used Like New";
            }
            case REGENERATED -> {
                return "Regenerated";
            }
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}
