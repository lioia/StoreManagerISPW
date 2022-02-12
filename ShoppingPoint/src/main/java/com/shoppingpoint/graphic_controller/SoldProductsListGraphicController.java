package com.shoppingpoint.graphic_controller;

import com.shoppingpoint.ShoppingPointApplication;
import com.shoppingpoint.controller.SummaryController;
import com.shoppingpoint.exception.ControllerException;
import com.shoppingpoint.model.SoldProduct;
import com.shoppingpoint.singleton.LoggedInUser;
import com.shoppingpoint.utils.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.List;

public class SoldProductsListGraphicController {
    @FXML
    private FlowPane soldProductsPane;

    @FXML
    public void initialize() throws IOException {
        SummaryController controller = new SummaryController();
        try {
            List<SoldProduct> products = controller.getSoldProducts();
            for (SoldProduct p : products) {
                FXMLLoader loader = new FXMLLoader(ShoppingPointApplication.class.getResource("reusable/sold_product_item.fxml"));
                AnchorPane pane = loader.load();
                ((Label) pane.lookup("#productName")).setText(p.getProduct().getName());
                ((Label) pane.lookup("#clientName")).setText(p.getClient().getUsername());
                ((Label) pane.lookup("#quantity")).setText("Quantity: " + p.getQuantity());
                ((Label) pane.lookup("#date")).setText(p.getDate().toString());
                soldProductsPane.getChildren().add(pane);
            }
        } catch (ControllerException e) {
            ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT, e.getMessage());
        }
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(ShoppingPointApplication.class.getResource("store_dashboard.fxml"));
        ((Node) event.getSource()).getScene().setRoot(loader.load());
    }

    @FXML
    public void logout(ActionEvent event) throws IOException {
        LoggedInUser.getInstance().setUser(null);
        FXMLLoader loader = new FXMLLoader(ShoppingPointApplication.class.getResource("login.fxml"));
        ((Node) event.getSource()).getScene().setRoot(loader.load());
    }
}
