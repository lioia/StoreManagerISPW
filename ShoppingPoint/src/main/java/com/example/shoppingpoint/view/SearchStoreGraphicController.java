package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.bean.SearchStoreBean;
import com.example.shoppingpoint.controller.SearchStoreController;
import com.example.shoppingpoint.model.Store;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchStoreGraphicController {

    private SearchStoreController controller;

    @FXML
    private FlowPane storesPane;

    //    This method gets called when loading the view
    @FXML
    public void initialize() throws Exception {
        controller = new SearchStoreController();

        SearchStoreBean bean = new SearchStoreBean();

        ArrayList<Store> stores = controller.getStores(bean);

        for (Store store : stores) {
            FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("reusable/store_pane.fxml"));
            Pane node = fxmlLoader.load();
            ((Text) node.lookup("#storeName")).setText(store.getName());
            ((Text) node.lookup("#storeAddress")).setText(store.getAddress());
            ((Text) node.lookup("#storeType")).setText("Type: " + store.getType().name().toLowerCase());
            storesPane.getChildren().add(node);
        }
    }
}
