package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.singleton.LoggedInUser;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;


import com.example.shoppingpoint.model.user.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import com.example.shoppingpoint.model.ClientListData;
import com.example.shoppingpoint.controller.ClientListController;

import java.io.IOException;
import java.util.List;

public class ClientListGraphicController {

    @FXML
    private Label labelStoreName;

    @FXML
    private FlowPane clientPane;

    @FXML
    public void goBack(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("store_dashboard.fxml"));
        Parent node = fxmlLoader.load();
        ((Node) event.getSource()).getScene().setRoot(node);
    }

    @FXML
    public void initData() throws Exception {
        String storeName = ((StoreOwner) LoggedInUser.getInstance().getUser()).getStore().getName();

        labelStoreName.setText(storeName + " - Shopping Point");
        createClientListView(storeName);
    }

    private void createClientListView(String storeName) throws Exception {
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
                HostServices hostServices = ShoppingPointApplication.getInstance().getHostServices();
                hostServices.showDocument("mailto:" + client.getEmail());
            });
            ((Label) pane.lookup("#clientPointsText")).setText(String.format("Points: %d", client.getPoints()));
//            Add client to the view
            clientPane.getChildren().add(pane);
        }
    }

    @FXML
    protected void logout(ActionEvent event) throws IOException {
        LoggedInUser.getInstance().setUser(null);
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("login.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }
}
