package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.model.Store;
import com.example.shoppingpoint.utils.ExceptionHandler;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.io.IOException;

public class PaymentCompletedGraphicController {

    @FXML
    BorderPane root;

    private PauseTransition transition;

    private Store store;

    @FXML
    public void initialize(Store store)  {
        this.store = store;

        transition = new PauseTransition(Duration.seconds(3));
        transition.setOnFinished(event -> {
            try {
                navigate(root);
            } catch (IOException e) {
                ExceptionHandler.handleException("Could not go back", e.getMessage());
            }
        });
        transition.play();
    }

    @FXML
    public void redirect(ActionEvent actionEvent) throws IOException {
        transition.stop();
        navigate((Node) actionEvent.getSource());
    }

    private void navigate(Node parent) throws IOException {
        FXMLLoader loader = new FXMLLoader(ShoppingPointApplication.class.getResource("store.fxml"));
        Parent node = loader.load();
        parent.getScene().setRoot(node);
        StoreGraphicController storeGraphicController = loader.getController();
        storeGraphicController.initialize(store);
    }
}
