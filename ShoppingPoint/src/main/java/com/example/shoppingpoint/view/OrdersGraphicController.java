package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.bean.OrdersBean;
import com.example.shoppingpoint.controller.OrdersController;
import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.model.Review;
import com.example.shoppingpoint.model.SoldProduct;
import com.example.shoppingpoint.model.Store;
import com.example.shoppingpoint.singleton.LoggedInUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
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
    public void initialize(Store store) {
        this.store = store;

        try {
            titleLabel.setText("Orders from " + store.getName());
            OrdersController controller = new OrdersController();
            List<SoldProduct> orders = controller.getOrders(LoggedInUser.getInstance().getUser().getUsername(), store.getName());
            for (SoldProduct order : orders) {
                Review review = controller.getReview(LoggedInUser.getInstance().getUser().getUsername(), order.getProduct().getId());
                FXMLLoader loader = new FXMLLoader(ShoppingPointApplication.class.getResource("reusable/order.fxml"));
                AnchorPane pane = loader.load();
                ((Label) pane.lookup("#name")).setText(order.getProduct().getName());
                ((Label) pane.lookup("#price")).setText(String.format("Total Price: %.02f€", order.getProduct().getDiscountedPrice() * order.getQuantity()));
                ((Rating) pane.lookup("#rating")).setRating(review.getValue());
                ((Rating) pane.lookup("#rating")).ratingProperty().addListener((observable, oldValue, newValue) -> {
                    try {
                        OrdersBean bean = new OrdersBean(newValue.floatValue());
                        controller.updateReview(bean, review.getReviewId(), LoggedInUser.getInstance().getUser().getUsername(), order.getProduct().getId());
                    } catch (BeanException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Incorrect data");
                        alert.setContentText(e.getMessage());
                        alert.show();
                    } catch (Exception e) { // TODO handle controller exception
                        e.printStackTrace();
                    }
                });
                ordersPane.getChildren().add(pane);
            }
        } catch (Exception e) { // TODO handle controller exception
            e.printStackTrace();
        }
    }

    @FXML
    public void goBack(ActionEvent actionEvent) throws Exception {
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
