package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.bean.StoreBean;
import com.example.shoppingpoint.controller.StoreController;
import com.example.shoppingpoint.model.LoyaltyCard;
import com.example.shoppingpoint.model.Store;
import com.example.shoppingpoint.model.user.Client;
import com.example.shoppingpoint.singleton.LoggedInUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.Rating;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.util.List;

public class StoreGraphicController {
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

    public void initData(Store store) throws Exception {
        this.store = store;
        storeNameText.setText(store.getName() + " - Shopping Point");

        if (store.getPointsInEuro() != 0 && store.getEuroInPoints() != 0) {
            try {
                this.card = controller.getLoyaltyCard(LoggedInUser.getInstance().getUser().getUsername(), store.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (card != null) {
                loyaltyCardButton.setVisible(false);
                currentPointsText.setVisible(true);
                currentPointsText.setText(String.format("You have %d points.", card.getPoints()));
                ((HBox) loyaltyCardButton.getParent()).getChildren().remove(loyaltyCardButton);
            } else {
                loyaltyCardButton.setVisible(true);
                currentPointsText.setVisible(false);
            }
            loyaltyCard1Text.setVisible(true);
            loyaltyCard1Text.setText(String.format("Every %d points used, you will have a discount of 1€", store.getPointsInEuro()));
            loyaltyCard2Text.setVisible(true);
            loyaltyCard2Text.setText(String.format("Every %d€ spent, you will receive 1 point", store.getEuroInPoints()));
        } else {
            ((HBox) loyaltyCardButton.getParent()).getChildren().remove(loyaltyCardButton);
            ((HBox) loyaltyCard1Text.getParent()).getChildren().remove(loyaltyCard1Text);
            ((VBox) loyaltyCard2Text.getParent()).getChildren().remove(loyaltyCard2Text);
            ((HBox) currentPointsText.getParent()).getChildren().remove(currentPointsText);
        }

        createProductsView(new StoreBean(store.getName()));
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("searchstore.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }

    @FXML
    public void search() throws Exception {
        String searchQuery = searchTextField.getText();

        createProductsView(new StoreBean(store.getName(), searchQuery));
    }

    private void createProductsView(StoreBean bean) throws Exception {
        productsPane.getChildren().clear();
        List<GenericProduct> products = controller.getProductsFromStore(bean);

        for (GenericProduct product : products) {
            if (product.getQuantity() == 0) continue;
            float reviewAverage = controller.getReviewOfProduct(product.getId());
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
            ((Rating) pane.lookup("#rating")).setRating(reviewAverage);
            ((Button) pane.lookup("#descriptionButton")).setOnAction((ActionEvent event) -> {
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
            ((Button) pane.lookup("#buyButton")).setOnAction((ActionEvent event) -> {
                try {
                    FXMLLoader paymentLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("payment.fxml"));
                    Parent node = paymentLoader.load();
                    ((Node) event.getSource()).getScene().setRoot(node);
                    PaymentGraphicController paymentController = paymentLoader.getController();
                    paymentController.initData(product, store, card);
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
        this.card = controller.createLoyaltyCard(LoggedInUser.getInstance().getUser().getUsername(), store.getName());
        loyaltyCardButton.setVisible(false);
        currentPointsText.setVisible(true);
        currentPointsText.setText(String.format("You have %d points.", card.getPoints()));
        ((HBox) loyaltyCardButton.getParent()).getChildren().remove(loyaltyCardButton);
    }


    @FXML
    public void goOrderList(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(ShoppingPointApplication.class.getResource("orders.fxml"));
        Parent node = loader.load();
        ((Node) event.getSource()).getScene().setRoot(node);
        OrdersGraphicController ordersGraphicController = loader.getController();
        ordersGraphicController.initData(store);
    }

    public void logout(ActionEvent event) throws Exception {
        LoggedInUser.getInstance().setUser(null);
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("login.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }
}
