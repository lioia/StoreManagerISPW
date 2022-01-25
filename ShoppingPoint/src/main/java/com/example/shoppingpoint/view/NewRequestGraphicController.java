package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.bean.NewRequestBean;
import com.example.shoppingpoint.controller.NewRequestController;
import com.example.shoppingpoint.model.user.StoreOwner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NewRequestGraphicController {
    private StoreOwner storeOwner;
    private GenericProduct product;

    @FXML
    private Label productNameLabel;
    @FXML
    private TextField maxPriceTextField;
    @FXML
    private TextField quantityTextField;

    public void initData(StoreOwner storeOwner, GenericProduct product) {
        this.storeOwner = storeOwner;
        this.product = product;
        productNameLabel.setText(product.getName());
    }

    @FXML
    public void goBack(ActionEvent actionEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader(ShoppingPointApplication.class.getResource("store_dashboard.fxml"));
        Parent node = loader.load();
        ((Node) actionEvent.getSource()).getScene().setRoot(node);
        StoreDashboardGraphicController storeDashboardGraphicController = loader.getController();
        storeDashboardGraphicController.initData(storeOwner);
    }

    @FXML
    public void openAccountInfo(ActionEvent actionEvent) {
//        TODO
    }

    @FXML
    public void save(ActionEvent actionEvent) throws Exception {
        NewRequestBean bean = new NewRequestBean(maxPriceTextField.getText(), quantityTextField.getText());
        NewRequestController controller = new NewRequestController();
        controller.saveRequest(bean, product.getId());
//        TODO go to offers view
    }
}
