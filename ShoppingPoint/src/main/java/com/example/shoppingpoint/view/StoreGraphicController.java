package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.bean.StoreBean;
import com.example.shoppingpoint.controller.StoreController;
import com.example.shoppingpoint.model.LoyaltyCard;
import com.example.shoppingpoint.model.Store;
import com.example.shoppingpoint.model.user.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class StoreGraphicController {
    private Client client;
    private Store store;
    private final StoreController controller;
    private LoyaltyCard card;

    @FXML
    private Label storeNameText;
    @FXML
    private Label loyaltyCard1Text;
    @FXML
    private Label loyaltyCard2Text;
    @FXML
    private FlowPane productsPane;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button loyaltyCardButton;
    @FXML
    private Label currentPointsText;

    public StoreGraphicController() {
        controller = new StoreController();
    }

    public void initData(Store store, Client client) throws Exception {
        this.client = client;
        this.store = store;
        storeNameText.setText(store.getName() + " - Shopping Point");

        if (store.getPointsInEuro() != 0 && store.getEuroInPoints() != 0) {
            try {
                this.card = controller.getLoyaltyCard(client.getUsername(), store.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (card != null) {
                loyaltyCardButton.setVisible(false);
                currentPointsText.setVisible(true);
                currentPointsText.setText(String.format("You have %d points.", card.getPoints()));
                ((VBox)loyaltyCardButton.getParent()).getChildren().remove(loyaltyCardButton);
            } else {
                loyaltyCardButton.setVisible(true);
                currentPointsText.setVisible(false);
            }
            loyaltyCard1Text.setVisible(true);
            loyaltyCard1Text.setText(String.format("Every %d points used, you will have a discount of 1€", store.getPointsInEuro()));
            loyaltyCard2Text.setVisible(true);
            loyaltyCard2Text.setText(String.format("Every %d€ spent, you will receive 1 point", store.getEuroInPoints()));
        } else {
            ((VBox)loyaltyCardButton.getParent()).getChildren().remove(loyaltyCardButton);
            ((VBox)loyaltyCard1Text.getParent()).getChildren().remove(loyaltyCard1Text);
            ((VBox)loyaltyCard2Text.getParent()).getChildren().remove(loyaltyCard2Text);
            ((VBox)currentPointsText.getParent()).getChildren().remove(currentPointsText);
        }

        createProductsView(new StoreBean(store.getName()));
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("searchstore.fxml"));
        Parent node = fxmlLoader.load();
        ((Node) event.getSource()).getScene().setRoot(node);
        SearchStoreGraphicController searchStoreGraphicController  = fxmlLoader.getController();
        searchStoreGraphicController.setClient(client);
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
            if (product.getQuantity() == 0) continue;
            FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("reusable/store_product_pane.fxml"));
            AnchorPane pane = fxmlLoader.load();
//            Set product data in the View
            ((Label) pane.lookup("#name")).setText(product.getName());
            String formattedPrice = String.format("%.02f€", product.getPrice()); // Price with 2 decimal points
            ((Label) pane.lookup("#price")).setText(formattedPrice);
            String formattedDiscountedPrice = String.format("%.02f€", product.getDiscountedPrice()); // Price with 2 decimal points
            ((Label) pane.lookup("#discountedPrice")).setText(formattedDiscountedPrice);
            ((Label) pane.lookup("#status")).setText(product.getStatus());
            ((Label) pane.lookup("#description")).setText(product.getDescription());
            ((Button) pane.lookup("#buyButton")).setOnAction((ActionEvent event) -> {
                try {
                    FXMLLoader paymentLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("payment.fxml"));
                    Parent node = paymentLoader.load();
                    ((Node) event.getSource()).getScene().setRoot(node);
                    PaymentGraphicController paymentController = paymentLoader.getController();
                    paymentController.initData(client, product, store, card);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
//            Add product to the view
            productsPane.getChildren().add(pane);
        }
    }

    @FXML
    public void activate(ActionEvent actionEvent) throws Exception {
        this.card = controller.createLoyaltyCard(client.getUsername(), store.getName());
        loyaltyCardButton.setVisible(false);
        currentPointsText.setVisible(true);
        currentPointsText.setText(String.format("You have %d points.", card.getPoints()));
        ((VBox)loyaltyCardButton.getParent()).getChildren().remove(loyaltyCardButton);
    }
}
