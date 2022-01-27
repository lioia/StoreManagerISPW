package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.bean.add_product.AddProductCommonBean;
import com.example.shoppingpoint.model.user.StoreOwner;
import com.example.shoppingpoint.singleton.LoggedInUser;
import com.example.shoppingpoint.utils.ProductType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddProductGraphicController {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField discountedPriceTextField;
    @FXML
    private TextField quantityTextField;
    @FXML
    private ComboBox<String> statusComboBox;
    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    public void initialize() throws Exception {
        switch (((StoreOwner) LoggedInUser.getInstance().getUser()).getStore().getType()) {
            case CLOTHES -> typeComboBox.getItems().addAll("Clothes", "Shoes");
            case BOOKS -> typeComboBox.getItems().addAll("Book", "Comics");
            case VIDEOGAMES -> typeComboBox.getItems().addAll("Video Game", "Game Console");
            case ELECTRONICS -> typeComboBox.getItems().addAll("Home Appliances", "Computer");
            default -> throw new Exception("Unrecognized store type");
        }
    }

    @FXML
    public void goNext(ActionEvent actionEvent) throws Exception {
        AddProductCommonBean bean = new AddProductCommonBean(nameTextField.getText(), priceTextField.getText(), discountedPriceTextField.getText(), quantityTextField.getText(), statusComboBox.getValue());
        ProductType type;
        switch (typeComboBox.getValue()) {
            case "Clothes" -> type = ProductType.CLOTHES;
            case "Shoes" -> type = ProductType.SHOES;
            case "Book" -> type = ProductType.BOOK;
            case "Comics" -> type = ProductType.COMICS;
            case "Video Game" -> type = ProductType.VIDEOGAME;
            case "Game Console" -> type = ProductType.GAMECONSOLE;
            case "Home Appliances" -> type = ProductType.HOMEAPPLIANCES;
            case "Computer" -> type = ProductType.COMPUTER;
            default -> throw new Exception("Unsupported type");
        }

        FXMLLoader loader = new FXMLLoader(ShoppingPointApplication.class.getResource("add_product_continue.fxml"));
        Parent node = loader.load();
        ((Node) actionEvent.getSource()).getScene().setRoot(node);
        AddProductContinueGraphicController addProductContinueGraphicController = loader.getController();
        addProductContinueGraphicController.initialize(bean, type);
    }

    @FXML
    public void goBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(ShoppingPointApplication.class.getResource("store_dashboard.fxml"));
        ((Node) actionEvent.getSource()).getScene().setRoot(loader.load());
    }
    @FXML
    protected void logout(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("login.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }
}
