package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.controller.SummaryController;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.model.SoldProduct;
import com.example.shoppingpoint.singleton.LoggedInUser;
import com.example.shoppingpoint.utils.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.List;

import static com.example.shoppingpoint.utils.ExceptionHandler.CONTROLLER_HEADER_TEXT;

public class SoldProductsListGraphicController {
    @FXML
    private FlowPane soldProductsPane;

    @FXML
    public void initialize() throws IOException {
        SummaryController controller = new SummaryController();
        try {
            List<SoldProduct> products = controller.getSoldProducts();
            for(SoldProduct p : products) {
                FXMLLoader loader = new FXMLLoader(ShoppingPointApplication.class.getResource("reusable/sold_product_item.fxml"));
                AnchorPane pane = loader.load();
                ((Label)pane.lookup("#productName")).setText(p.getProduct().getName());
                ((Label)pane.lookup("#clientName")).setText(p.getClient().getUsername());
                ((Label)pane.lookup("#quantity")).setText("Quantity: " + p.getQuantity());
                ((Label)pane.lookup("#date")).setText(p.getDate().toString());
                soldProductsPane.getChildren().add(pane);
            }
        } catch (ControllerException e) {
            ExceptionHandler.handleException(CONTROLLER_HEADER_TEXT, e.getMessage());
        }
    }

    @FXML
    public void goBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(ShoppingPointApplication.class.getResource("store_dashboard.fxml"));
        ((Node)actionEvent.getSource()).getScene().setRoot(loader.load());
    }

    @FXML
    public void logout(ActionEvent actionEvent) throws IOException {
        LoggedInUser.getInstance().setUser(null);
        FXMLLoader loader = new FXMLLoader(ShoppingPointApplication.class.getResource("login.fxml"));
        ((Node)actionEvent.getSource()).getScene().setRoot(loader.load());
    }
}
