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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.Rating;
import org.kordamp.ikonli.javafx.FontIcon;

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

    @FXML
    public void openAccountInfo(ActionEvent actionEvent) {
        VBox vbox = new VBox(16);
        vbox.setPadding(new Insets(16));
        Button logoutButton = new Button("Log Out");
        logoutButton.setAlignment(Pos.CENTER);
        logoutButton.setStyle("-fx-background-color: #6EC6FF; -fx-background-radius: 16;");
        logoutButton.setOnAction(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("login.fxml"));
                ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        logoutButton.setEffect(new DropShadow());
        logoutButton.setGraphic(new FontIcon("mdal-log_out"));
        logoutButton.setPrefSize(120, 48);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(logoutButton);

        PopOver popOver = new PopOver();
        Node node = (Node) actionEvent.getSource();
        popOver.setArrowLocation(PopOver.ArrowLocation.TOP_CENTER);
        popOver.setContentNode(vbox);
        popOver.setCornerRadius(16);
        popOver.show(node);
    }
}
