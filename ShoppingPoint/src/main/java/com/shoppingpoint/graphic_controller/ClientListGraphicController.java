package com.shoppingpoint.graphic_controller;

import com.shoppingpoint.ShoppingPointApplication;
import com.shoppingpoint.controller.ClientListController;
import com.shoppingpoint.controller.SendEmailController;
import com.shoppingpoint.exception.ControllerException;
import com.shoppingpoint.exception.EmailException;
import com.shoppingpoint.model.ClientListData;
import com.shoppingpoint.model.user.StoreOwner;
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

public class ClientListGraphicController {

    @FXML
    private Label labelStoreName;

    @FXML
    private FlowPane clientPane;

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("store_dashboard.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }

    @FXML
    public void initialize() throws IOException {
        String storeName = ((StoreOwner) LoggedInUser.getInstance().getUser()).getStore().getName();

        labelStoreName.setText(storeName + " - Shopping Point");
        createClientListView(storeName);
    }

    private void createClientListView(String storeName) throws IOException {
        try {
            clientPane.getChildren().clear();
            ClientListController controller = new ClientListController();
            List<ClientListData> clients = controller.getClientFromStore(storeName);

            for (ClientListData client : clients) {
                FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("reusable/client_list_item.fxml"));
                AnchorPane pane = fxmlLoader.load();

                ((Label) pane.lookup("#clientNameText")).setText(client.getUsername());
                Label email = (Label) pane.lookup("#clientEmailText");
                email.setText(client.getEmail());
                email.setOnMouseClicked(event -> {
                    try {
                        new SendEmailController().sendEmail(client.getUsername());
                    } catch (ControllerException e) {
                        ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT, e.getMessage());
                    } catch (EmailException e) {
                        ExceptionHandler.handleException("Email", e.getMessage());
                    }
                });
                ((Label) pane.lookup("#clientPointsText")).setText(String.format("Points: %d", client.getPoints()));
//            Add client to the view
                clientPane.getChildren().add(pane);
            }
        } catch (ControllerException e) {
            ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT, e.getMessage());
        }
    }

    @FXML
    protected void logout(ActionEvent event) throws IOException {
        LoggedInUser.getInstance().setUser(null);
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("login.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }
}
