package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.bean.OrdersBean;
import com.example.shoppingpoint.controller.OrdersController;
import com.example.shoppingpoint.model.Review;
import com.example.shoppingpoint.model.SoldProduct;
import com.example.shoppingpoint.model.Store;
import com.example.shoppingpoint.model.user.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import org.controlsfx.control.Rating;

import java.util.List;

public class OrdersGraphicController {
    @FXML
    private Label titleLabel;
    @FXML
    private FlowPane ordersPane;

    private Store store;
    private Client client;

    public void initData(Client client, Store store) {
        this.store = store;
        this.client = client;
        try {
            titleLabel.setText("Orders from " + store.getName());
            OrdersController controller = new OrdersController();
            List<SoldProduct> orders = controller.getOrders(client.getUsername(), store.getName());
            for (SoldProduct order : orders) {
                Review review = controller.getReview(client.getUsername(), order.getProduct().getId());
                FXMLLoader loader = new FXMLLoader(ShoppingPointApplication.class.getResource("reusable/order.fxml"));
                AnchorPane pane = loader.load();
                ((Label) pane.lookup("#name")).setText(order.getProduct().getName());
                ((Label) pane.lookup("#price")).setText(String.format("Total Price: %.02f€", order.getProduct().getDiscountedPrice() * order.getQuantity()));
                ((Rating) pane.lookup("#rating")).setRating(review.getValue());
                ((Rating) pane.lookup("#rating")).ratingProperty().addListener((observable, oldValue, newValue) -> {
                    try {
                        OrdersBean bean = new OrdersBean(newValue.floatValue());
                        controller.updateReview(bean, review.getReviewId(), client.getUsername(), order.getProduct().getId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                ordersPane.getChildren().add(pane);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goBack(ActionEvent actionEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader(ShoppingPointApplication.class.getResource("store.fxml"));
        Parent node = loader.load();
        ((Node)actionEvent.getSource()).getScene().setRoot(node);
        StoreGraphicController storeGraphicController = loader.getController();
        storeGraphicController.initData(store, client);
    }
}
