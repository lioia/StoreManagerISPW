package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.bean.PaymentBean;
import com.example.shoppingpoint.controller.PaymentController;
import com.example.shoppingpoint.model.LoyaltyCard;
import com.example.shoppingpoint.model.Store;
import com.example.shoppingpoint.model.user.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class PaymentGraphicController {
    private Client client;
    private GenericProduct product;
    private Store store;
    private LoyaltyCard card;

    @FXML
    private Text productNameText;

    @FXML
    private TextField quantityTextField;

    @FXML
    private Text priceText;

    @FXML
    private Text totalText;

    @FXML
    private CheckBox checkLoyaltyCard;

    @FXML
    private Label maxQuantityText;

    private final String DECIMAL_FORMAT = "%.02f€";

    public void initData(Client client, GenericProduct product, Store store, LoyaltyCard card) {
        this.client = client;
        this.product = product;
        this.store = store;
        this.card = card;

        productNameText.setText(product.getName());
        String formattedPrice = String.format(DECIMAL_FORMAT, product.getDiscountedPrice()); // Price with 2 decimal points
        priceText.setText(formattedPrice);
        Float total = Integer.parseInt(quantityTextField.getText()) * product.getDiscountedPrice();
        String formattedTotal = String.format(DECIMAL_FORMAT, total);
        totalText.setText(formattedTotal);
        checkLoyaltyCard.setVisible(card != null);
        maxQuantityText.setText(maxQuantityText.getText() + product.getQuantity());
    }

    @FXML
    public void buy(ActionEvent actionEvent) throws Exception {

        PaymentController controller = new PaymentController();
        PaymentBean bean = new PaymentBean(quantityTextField.getText(), checkLoyaltyCard.isSelected());
        controller.buy(bean, card, client.getUsername(), store, product);
//        TODO go to payment completed
    }

    @FXML
    public void loyaltyCardCheck(ActionEvent actionEvent) {
        if (checkLoyaltyCard.isSelected()) {
            float total = Integer.parseInt(quantityTextField.getText()) * product.getDiscountedPrice() -  card.getPoints()/store.getPointsInEuro() ;
            if (total < 0) {
                total = 0f;
            }
            String formattedTotal = String.format(DECIMAL_FORMAT, total);
            totalText.setText(formattedTotal);
        } else {
            Float total = Integer.parseInt(quantityTextField.getText()) * product.getDiscountedPrice();
            String formattedTotal = String.format(DECIMAL_FORMAT, total);
            totalText.setText(formattedTotal);
        }
    }

    @FXML
    public void goBack(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("store.fxml"));
        Parent node = fxmlLoader.load();
        ((Node) event.getSource()).getScene().setRoot(node);
        StoreGraphicController storeGraphicController = fxmlLoader.getController();
        storeGraphicController.initData(store, client);
    }
}
