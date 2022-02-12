package com.shoppingpoint.graphic_controller;

import com.shoppingpoint.ShoppingPointApplication;
import com.shoppingpoint.adapter.GenericProduct;
import com.shoppingpoint.bean.StoreBean;
import com.shoppingpoint.controller.LoyaltyCardController;
import com.shoppingpoint.controller.PaymentController;
import com.shoppingpoint.controller.ReviewController;
import com.shoppingpoint.controller.SendEmailController;
import com.shoppingpoint.exception.ControllerException;
import com.shoppingpoint.exception.DatabaseException;
import com.shoppingpoint.exception.EmailException;
import com.shoppingpoint.model.LoyaltyCard;
import com.shoppingpoint.model.Store;
import com.shoppingpoint.singleton.LoggedInUser;
import com.shoppingpoint.utils.DescriptionHandler;
import com.shoppingpoint.utils.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import org.controlsfx.control.Rating;

import java.io.IOException;
import java.util.List;

public class StoreGraphicController {
    private Store store;
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

    @FXML
    public void initialize(Store store) throws IOException {
        this.store = store;
        storeNameText.setText(store.getName() + " - Shopping Point");

        if (store.getPointsInEuro() != 0 && store.getEuroInPoints() != 0) {
            try {
                PaymentController controller = new PaymentController();
                this.card = controller.getLoyaltyCard(LoggedInUser.getInstance().getUser().getUsername(), store.getName());
            } catch (Exception ignored) {
                this.card = null;
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
            ((HBox) loyaltyCard2Text.getParent()).getChildren().remove(loyaltyCard2Text);
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
    public void search() throws IOException, DatabaseException {
        String searchQuery = searchTextField.getText();

        createProductsView(new StoreBean(store.getName(), searchQuery));
    }

    @FXML
    public void sendEmail() {
        SendEmailController emailController = new SendEmailController();
        String username;
        try {
            username = emailController.getUsernameOfStore(store.getName());
            emailController.sendEmail(username);
        } catch (ControllerException e) {
            ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT, e.getMessage());
        } catch (EmailException e) {
            ExceptionHandler.handleException("Email", e.getMessage());
        }
    }

    private void createProductsView(StoreBean bean) throws IOException {
        try {
            productsPane.getChildren().clear();
            PaymentController controller = new PaymentController();
            List<GenericProduct> products = controller.getProductsFromStore(bean);

            for (GenericProduct product : products) {
                if (product.getQuantity() == 0) continue;
                ReviewController reviewController = new ReviewController();
                float reviewAverage = reviewController.getAverageReviewOfProduct(product.getId());
                FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("reusable/store_product_pane.fxml"));
                AnchorPane pane = fxmlLoader.load();
//            Set product data in the View
                ((Label) pane.lookup("#name")).setText(product.getName());
                String formattedPrice = String.format("Price: %.02f€", product.getPrice()); // Price with 2 decimal points
                ((Label) pane.lookup("#price")).setText(formattedPrice);
                String formattedDiscountedPrice = String.format("Discount: %.02f€ (%.02f%%)", product.getDiscountedPrice(),controller.calculateDiscountPercentage(product.getPrice(),product.getDiscountedPrice())); // Price with 2 decimal points
                ((Label) pane.lookup("#discountedPrice")).setText(formattedDiscountedPrice);
                ((Label) pane.lookup("#status")).setText(product.getStatus());
                ((Label) pane.lookup("#description")).setText(product.getDescription());
                ((Rating) pane.lookup("#rating")).setRating(reviewAverage);
                if (product.getImage() != null)
                    ((ImageView) pane.lookup("#imageView")).setImage(new Image(product.getImage()));
                ((Button) pane.lookup("#descriptionButton")).setOnAction((ActionEvent event) -> DescriptionHandler.showDescription(event, product.getDescription()));
                ((Button) pane.lookup("#buyButton")).setOnAction((ActionEvent event) -> {
                    try {
                        FXMLLoader paymentLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("payment.fxml"));
                        Parent node = paymentLoader.load();
                        ((Node) event.getSource()).getScene().setRoot(node);
                        PaymentGraphicController paymentController = paymentLoader.getController();
                        paymentController.initialize(product, store, card);
                    } catch (IOException e) {
                        ExceptionHandler.handleException("Could not go to next scene", e.getMessage());
                    }
                });
//            Add product to the view
                productsPane.getChildren().add(pane);
            }
        } catch (ControllerException e) {
            ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT, e.getMessage());
        }
    }

    @FXML
    public void activate() {
        try {
            LoyaltyCardController loyaltyCardController = new LoyaltyCardController();
            this.card = loyaltyCardController.createLoyaltyCard(LoggedInUser.getInstance().getUser().getUsername(), store.getName());
        } catch (ControllerException e) {
            ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT, e.getMessage());
        }
        loyaltyCardButton.setVisible(false);
        currentPointsText.setVisible(true);
        currentPointsText.setText(String.format("You have %d points.", card.getPoints()));
        ((HBox) loyaltyCardButton.getParent()).getChildren().remove(loyaltyCardButton);
    }

    @FXML
    public void goOrderList(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(ShoppingPointApplication.class.getResource("orders.fxml"));
        Parent node = loader.load();
        ((Node) event.getSource()).getScene().setRoot(node);
        OrdersGraphicController ordersGraphicController = loader.getController();
        ordersGraphicController.initialize(store);
    }

    public void logout(ActionEvent event) throws IOException {
        LoggedInUser.getInstance().setUser(null);
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("login.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }
}
