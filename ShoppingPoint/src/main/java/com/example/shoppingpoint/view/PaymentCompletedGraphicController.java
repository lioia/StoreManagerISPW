package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.model.Store;
import com.example.shoppingpoint.model.user.Client;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class PaymentCompletedGraphicController {

    @FXML
    BorderPane root;

    private PauseTransition transition;

    private Client client;
    private Store store;

    public void initData(Client client, Store store) {
        this.client = client;
        this.store = store;

        transition = new PauseTransition(Duration.seconds(3));
        transition.setOnFinished(event -> navigate(root));
        transition.play();
    }

    @FXML
    public void redirect(ActionEvent actionEvent) {
        transition.stop();
        navigate((Node) actionEvent.getSource());
    }

    private void navigate(Node parent) {
        try {
            FXMLLoader loader = new FXMLLoader(ShoppingPointApplication.class.getResource("store.fxml"));
            Parent node = loader.load();
            parent.getScene().setRoot(node);
            StoreGraphicController storeGraphicController = loader.getController();
            storeGraphicController.initData(store, client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
