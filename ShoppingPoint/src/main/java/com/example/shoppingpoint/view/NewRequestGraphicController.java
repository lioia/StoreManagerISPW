package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.bean.NewRequestBean;
import com.example.shoppingpoint.controller.NewRequestController;
import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.model.user.StoreOwner;
import com.example.shoppingpoint.singleton.LoggedInUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class NewRequestGraphicController {
    private GenericProduct product;

    @FXML
    private Label productNameLabel;
    @FXML
    private TextField maxPriceTextField;
    @FXML
    private TextField quantityTextField;

    public void initialize(GenericProduct product) {
        this.product = product;
        productNameLabel.setText(product.getName());
    }

    @FXML
    public void goBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(ShoppingPointApplication.class.getResource("store_dashboard.fxml"));
        ((Node) actionEvent.getSource()).getScene().setRoot(loader.load());
    }

    @FXML
    public void save(ActionEvent actionEvent) {
        try {
            NewRequestBean bean = new NewRequestBean(maxPriceTextField.getText(), quantityTextField.getText());
            NewRequestController controller = new NewRequestController();
            controller.saveRequest(bean, product.getId());
            FXMLLoader loader = new FXMLLoader(ShoppingPointApplication.class.getResource("offers.fxml"));
            Parent node = loader.load();
            ((Node) actionEvent.getSource()).getScene().setRoot(node);
            OffersGraphicController offersGraphicController = loader.getController();
            offersGraphicController.initData(product);
        } catch (BeanException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Incorrect data");
            alert.setContentText(e.getMessage());
            alert.show();
        } catch (Exception e) { // TODO handle controller exception
            e.printStackTrace();
        }
    }

    @FXML
    protected void logout(ActionEvent event) throws IOException {
        LoggedInUser.getInstance().setUser(null);
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("login.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }
}
