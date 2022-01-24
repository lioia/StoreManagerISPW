package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
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
import com.example.shoppingpoint.utils.ClientListData;
import com.example.shoppingpoint.controller.ClientListController;

import java.util.List;

public class ClientListGraphicController {

    private StoreOwner storeOwner;

    @FXML
    private Label labelStoreName;

    @FXML
    private FlowPane clientPane;

    @FXML
    public void goBack(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("store_dashboard.fxml"));
        Parent node = fxmlLoader.load();
        ((Node) event.getSource()).getScene().setRoot(node);
        StoreDashboardGraphicController storeDashboardController = fxmlLoader.getController();
        storeDashboardController.initData(storeOwner);
    }

    @FXML
    public void initData(StoreOwner storeOwner) throws Exception {
        this.storeOwner = storeOwner;
        String storeName = storeOwner.getStore().getName();

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
}
