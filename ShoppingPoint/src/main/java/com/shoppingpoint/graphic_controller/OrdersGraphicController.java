package com.shoppingpoint.graphic_controller;

import com.shoppingpoint.ShoppingPointApplication;
import com.shoppingpoint.bean.OrdersBean;
import com.shoppingpoint.controller.ReviewController;
import com.shoppingpoint.exception.BeanException;
import com.shoppingpoint.exception.ControllerException;
import com.shoppingpoint.exception.DatabaseException;
import com.shoppingpoint.model.Review;
import com.shoppingpoint.model.SoldProduct;
import com.shoppingpoint.model.Store;
import com.shoppingpoint.singleton.LoggedInUser;
import com.shoppingpoint.utils.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import org.controlsfx.control.Rating;

import java.io.IOException;
import java.util.List;

public class OrdersGraphicController {
    @FXML
    private Label titleLabel;
    @FXML
    private FlowPane ordersPane;

    private Store store;

    @FXML
    public void initialize(Store store) throws IOException {
        this.store = store;

        try {
            titleLabel.setText("Orders from " + store.getName());
            ReviewController controller = new ReviewController();
            List<SoldProduct> orders = controller.getOrders(LoggedInUser.getInstance().getUser().getUsername(), store.getName());
            for (SoldProduct order : orders) {
                Review review = controller.getReview(LoggedInUser.getInstance().getUser().getUsername(), order);
                FXMLLoader loader = new FXMLLoader(ShoppingPointApplication.class.getResource("reusable/order.fxml"));
                AnchorPane pane = loader.load();
                ((Label) pane.lookup("#name")).setText(order.getProduct().getName());
                ((Label) pane.lookup("#date")).setText(order.getDate().toString());
                ((Label) pane.lookup("#price")).setText(String.format("Total Price: %.02f€", order.getProduct().getDiscountedPrice() * order.getQuantity()));
                ((Rating) pane.lookup("#rating")).setRating(review.getValue());
                ((Rating) pane.lookup("#rating")).ratingProperty().addListener((observable, oldValue, newValue) -> {
                    try {
                        OrdersBean bean = new OrdersBean(newValue.floatValue());
                        controller.updateReview(bean, review.getReviewId(), LoggedInUser.getInstance().getUser().getUsername(), order);
                    } catch (BeanException e) {
                        ExceptionHandler.handleException(ExceptionHandler.BEAN_HEADER_TEXT, e.getMessage());
                    } catch (ControllerException e) {
                        ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT, e.getMessage());
                    }
                });
                ordersPane.getChildren().add(pane);
            }
        } catch (ControllerException e) {
            ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT, e.getMessage());
        }
    }

    @FXML
    public void goBack(ActionEvent actionEvent) throws IOException, DatabaseException {
        FXMLLoader loader = new FXMLLoader(ShoppingPointApplication.class.getResource("store.fxml"));
        Parent node = loader.load();
        ((Node) actionEvent.getSource()).getScene().setRoot(node);
        StoreGraphicController storeGraphicController = loader.getController();
        storeGraphicController.initialize(store);
    }

    @FXML
    protected void logout(ActionEvent event) throws IOException {
        LoggedInUser.getInstance().setUser(null);
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("login.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }
}
