package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.controller.StoreDashboardController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;


import com.example.shoppingpoint.model.user.*;
import com.example.shoppingpoint.model.Store;
import com.example.shoppingpoint.dao.StoreDAO;
import com.example.shoppingpoint.view.ClientListGraphicController;
import javafx.scene.text.Text;
import org.controlsfx.control.PopOver;

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
            ((Label) pane.lookup("#name")).setText(product.getName());
            String formattedPrice = String.format("%.02f€", product.getPrice()); // Price with 2 decimal points
            ((Label) pane.lookup("#price")).setText(formattedPrice);
            String formattedDiscountedPrice = String.format("%.02f€", product.getDiscountedPrice()); // Price with 2 decimal points
            ((Label) pane.lookup("#discountedPrice")).setText(formattedDiscountedPrice);
            ((Label) pane.lookup("#status")).setText(product.getStatus());
            ((Label) pane.lookup("#description")).setText(product.getDescription());
            ((Button) pane.lookup("#descriptionButtonOfLabel")).setOnAction((ActionEvent event) -> {
                ScrollPane scrollPane = new ScrollPane();
                scrollPane.setMaxWidth(400.0);
                scrollPane.setMaxHeight(400.0);
                scrollPane.setPadding(new Insets(16));
                Label label = new Label();
                label.setText(product.getDescription());
                label.setStyle("-fx-font-size: 16px");
                label.setMaxWidth(350.0);
                label.setWrapText(true);
                scrollPane.setContent(label);
                PopOver popOver = new PopOver();
                Node node = (Node) event.getSource();
                popOver.setArrowLocation(PopOver.ArrowLocation.TOP_LEFT);
                popOver.setContentNode(scrollPane);
                popOver.setCornerRadius(16);
                popOver.show(node);
            });
            ((Button) pane.lookup("#editButton")).setOnAction((ActionEvent event) -> {
                ((Label) pane.lookup("#name")).setVisible(false);
                ((Label) pane.lookup("#price")).setVisible(false);
                ((Label)pane.lookup("#discountedPrice")).setVisible(false);
                ((Label) pane.lookup("#status")).setVisible(false);
                ((Button) pane.lookup("#editButton")).setVisible(false);
                ((Button) pane.lookup("#requestButton")).setVisible(false);
                ((HBox)pane.lookup("#descriptionHbox")).setVisible(false);

                ((TextField) pane.lookup("#nameTextField")).setVisible(true);
                ((TextField) pane.lookup("#priceTextField")).setVisible(true);
                ((TextField) pane.lookup("#discountedPriceTextField")).setVisible(true);
                ((TextField) pane.lookup("#statusTextField")).setVisible(true);
                ((TextField) pane.lookup("#descriptionTextField")).setVisible(true);
                ((Button)pane.lookup("#saveButton")).setVisible((true));



                ((TextField) pane.lookup("#nameTextField")).setText(product.getName());
                String formattedPriceTextField = String.format("%.02f€", product.getPrice()); // Price with 2 decimal points
                ((TextField) pane.lookup("#priceTextField")).setText(formattedPriceTextField);
                String formattedDiscountedPriceTextField = String.format("%.02f€", product.getDiscountedPrice()); // Price with 2 decimal points
                ((TextField) pane.lookup("#discountedPriceTextField")).setText(formattedDiscountedPriceTextField);
                ((TextField) pane.lookup("#statusTextField")).setText(product.getStatus());
                ((TextField) pane.lookup("#descriptionTextField")).setText(product.getDescription());
                ((Button) pane.lookup("#saveButton")).setOnAction((ActionEvent actionEvent) -> {
                    ((Label) pane.lookup("#name")).setVisible(true);
                    ((Label) pane.lookup("#price")).setVisible(true);
                    ((Label)pane.lookup("#discountedPrice")).setVisible(true);
                    ((Label) pane.lookup("#status")).setVisible(true);
                    ((Button) pane.lookup("#editButton")).setVisible(true);
                    ((Button) pane.lookup("#requestButton")).setVisible(true);
                    ((HBox)pane.lookup("#descriptionHbox")).setVisible(true);

                    ((TextField) pane.lookup("#nameTextField")).setVisible(false);
                    ((TextField) pane.lookup("#priceTextField")).setVisible(false);
                    ((TextField) pane.lookup("#discountedPriceTextField")).setVisible(false);
                    ((TextField) pane.lookup("#statusTextField")).setVisible(false);
                    ((TextField) pane.lookup("#descriptionTextField")).setVisible(false);
                    ((Button)pane.lookup("#saveButton")).setVisible(false);
                });

            });
//            Add product to the view
            productsPane.getChildren().add(pane);
        }
    }

    @FXML
    public void openEditCard(ActionEvent actionEvent) {
        VBox vbox = new VBox(16);
        vbox.setPadding(new Insets(16));
        CheckBox activeBox = new CheckBox("Active");
        activeBox.setAlignment(Pos.CENTER);
        HBox hbox1 = new HBox();
        HBox hbox2 = new HBox();
        TextField pointInEuroTextField = new TextField();
        pointInEuroTextField.setPromptText("1");
        pointInEuroTextField.setPrefHeight(12);
        pointInEuroTextField.setPrefWidth(20);
        TextField euroInPointsTextField = new TextField();
        euroInPointsTextField.setPromptText("1");
        euroInPointsTextField.setPrefWidth(20);
        euroInPointsTextField.setPrefHeight(6);
        Text text1 = new Text("poits spent = 1€ discount");
        Text text2 = new Text("€ spent = 1 point earned");
        hbox1.getChildren().addAll(pointInEuroTextField,text1);
        hbox2.getChildren().addAll(euroInPointsTextField,text2);


        //TODO sistemare textfield fare la bean e controll

        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(activeBox,hbox1,hbox2);


        PopOver popOver = new PopOver();
        Node node = (Node) actionEvent.getSource();
        popOver.setArrowLocation(PopOver.ArrowLocation.TOP_CENTER);
        popOver.setContentNode(vbox);
        popOver.setCornerRadius(16);
        popOver.show(node);
    }

}
