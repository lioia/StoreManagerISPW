package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.bean.store_dashboard.LoyaltyCardBean;
import com.example.shoppingpoint.controller.StoreDashboardController;
import com.example.shoppingpoint.singleton.LoggedInUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;


import com.example.shoppingpoint.model.user.*;
import com.example.shoppingpoint.model.Store;
import javafx.scene.text.Font;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.Rating;

import java.io.IOException;
import java.util.List;

public class StoreDashboardGraphicController {

    @FXML
    private Label labelStoreName;

    private final StoreDashboardController controller;

    @FXML
    private FlowPane productsPane;

    public StoreDashboardGraphicController() {
        controller = new StoreDashboardController();
    }

    @FXML
    public void initialize() throws Exception {
        Store store = controller.getStoreFromStoreOwnerName(LoggedInUser.getInstance().getUser().getUsername());
        ((StoreOwner) LoggedInUser.getInstance().getUser()).setStore(store);
        labelStoreName.setText(((StoreOwner) LoggedInUser.getInstance().getUser()).getStore().getName() + " - Shopping Point");
        createProductsView(((StoreOwner) LoggedInUser.getInstance().getUser()).getStore());
    }

    @FXML
    public void goToClientList(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("client_list.fxml"));
        Parent node = fxmlLoader.load();
        ((Node) event.getSource()).getScene().setRoot(node);
        ClientListGraphicController clientListGraphicController = fxmlLoader.getController();
        clientListGraphicController.initData();
    }

    private void createProductsView(Store store) throws Exception {
        productsPane.getChildren().clear();
        List<GenericProduct> products = controller.getProductsFromStore(store);

        for (GenericProduct product : products) {
            FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("reusable/store_dashboard_product_pane.fxml"));
            float reviewAverage = controller.getReviewOfProduct(product.getId());
            AnchorPane pane = fxmlLoader.load();
//            Set product data in the View
            ((Label) pane.lookup("#name")).setText(product.getName());
            String formattedPrice = String.format("%.02f€", product.getPrice()); // Price with 2 decimal points
            ((Label) pane.lookup("#price")).setText("price: "+formattedPrice);
            String formattedDiscountedPrice = String.format("%.02f€", product.getDiscountedPrice()); // Price with 2 decimal points
            ((Label) pane.lookup("#discountedPrice")).setText("dicounted price: "+formattedDiscountedPrice);
            ((Label) pane.lookup("#status")).setText(product.getStatus());
            ((Label) pane.lookup("#description")).setText(product.getDescription());
            ((Label) pane.lookup("#quantity")).setText(String.format("quantity: %d",product.getQuantity()));
            ((Rating) pane.lookup("#rating")).setRating(reviewAverage);
            ((Button) pane.lookup("#descriptionButtonOfLabel")).setOnAction((ActionEvent event) -> {
                ScrollPane scrollPane = new ScrollPane();
                scrollPane.setMaxWidth(400.0);
                scrollPane.setMaxHeight(400.0);
                scrollPane.setPadding(new Insets(16));
                Label label = new Label();
                label.setText(product.getDescription());
                label.setStyle("-fx-font-size: 16px");
                label.setMaxWidth(350.0);
                label.setWrapText(true);
                scrollPane.setContent(label);
                PopOver popOver = new PopOver();
                Node node = (Node) event.getSource();
                popOver.setArrowLocation(PopOver.ArrowLocation.TOP_LEFT);
                popOver.setContentNode(scrollPane);
                popOver.setCornerRadius(16);
                popOver.show(node);
            });
            ((Button) pane.lookup("#requestButton")).setOnAction(event -> {
                try {
                    FXMLLoader loader = new FXMLLoader(ShoppingPointApplication.class.getResource("new_request.fxml"));
                    Parent node = loader.load();
                    ((Node) event.getSource()).getScene().setRoot(node);
                    NewRequestGraphicController newRequestGraphicController = loader.getController();
                    newRequestGraphicController.initData(product);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            ((Button) pane.lookup("#offersButton")).setOnAction(event -> {
                try {
                    FXMLLoader loader = new FXMLLoader(ShoppingPointApplication.class.getResource("offers.fxml"));
                    Parent node = loader.load();
                    ((Node) event.getSource()).getScene().setRoot(node);
                    OffersGraphicController offersGraphicController = loader.getController();
                    offersGraphicController.initData(product);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            ((Button) pane.lookup("#editButton")).setOnAction((ActionEvent event) -> {
                setProductVisibility(pane, false);
                ((Button) pane.lookup("#saveButton")).setOnAction((ActionEvent actionEvent) -> {
                    setProductVisibility(pane, true);
//                    TODO update product
                });

            });
//            Add product to the view
            productsPane.getChildren().add(pane);
        }
    }

    @FXML
    public void openEditCard(ActionEvent actionEvent) {
        PopOver popOver = new PopOver();

        Font font = new Font(18);
        VBox vbox = new VBox(16);
        vbox.setPadding(new Insets(16));
        CheckBox activeBox = new CheckBox("Active");
        activeBox.setAlignment(Pos.CENTER);
        activeBox.setFont(font);
        activeBox.setSelected(((StoreOwner) LoggedInUser.getInstance().getUser()).getStore().getPointsInEuro() != 0 && ((StoreOwner) LoggedInUser.getInstance().getUser()).getStore().getEuroInPoints() != 0);

        HBox hbox1 = new HBox();
        HBox hbox2 = new HBox();
        TextField pointInEuroTextField = new TextField(((StoreOwner) LoggedInUser.getInstance().getUser()).getStore().getPointsInEuro().toString());
        pointInEuroTextField.setPrefSize(64, 32);
        pointInEuroTextField.setDisable(!activeBox.isSelected());
        TextField euroInPointsTextField = new TextField(((StoreOwner) LoggedInUser.getInstance().getUser()).getStore().getEuroInPoints().toString());
        euroInPointsTextField.setPrefSize(64, 32);
        euroInPointsTextField.setDisable(!activeBox.isSelected());
        Label text1 = new Label(" points spent = 1€ discount");
        text1.setAlignment(Pos.CENTER);
        text1.setFont(font);
        Label text2 = new Label(" € spent = 1 point earned");
        text2.setAlignment(Pos.CENTER);
        text2.setFont(font);
        hbox1.getChildren().addAll(pointInEuroTextField, text1);
        hbox2.getChildren().addAll(euroInPointsTextField, text2);

        activeBox.setOnAction(event -> {
            boolean selected = ((CheckBox) event.getSource()).isSelected();
            pointInEuroTextField.setDisable(!selected);
            euroInPointsTextField.setDisable(!selected);
        });

        Button updateButton = new Button("Update");
        updateButton.setAlignment(Pos.CENTER);
        updateButton.setStyle("-fx-background-color: #6EC6FF; -fx-background-radius: 16;");
        updateButton.setEffect(new DropShadow());
        updateButton.setPrefSize(120, 48);
        updateButton.setOnAction(event -> {
            try {
                LoyaltyCardBean bean = new LoyaltyCardBean(activeBox.isSelected(), pointInEuroTextField.getText(), euroInPointsTextField.getText());
                controller.updateLoyaltyCard(bean, ((StoreOwner) LoggedInUser.getInstance().getUser()).getStore());
                ((StoreOwner) LoggedInUser.getInstance().getUser()).getStore().setPointsInEuro(bean.getPointsInEuro());
                ((StoreOwner) LoggedInUser.getInstance().getUser()).getStore().setEuroInPoints(bean.getEuroInPoints());
                popOver.hide();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(activeBox, hbox1, hbox2, updateButton);

        Node node = (Node) actionEvent.getSource();
        popOver.setArrowLocation(PopOver.ArrowLocation.TOP_CENTER);
        popOver.setContentNode(vbox);
        popOver.setCornerRadius(16);
        popOver.show(node);
    }

    private void setProductVisibility(AnchorPane pane, boolean visibility) {
        pane.lookup("#name").setVisible(visibility);
        pane.lookup("#price").setVisible(visibility);
        pane.lookup("#discountedPrice").setVisible(visibility);
        pane.lookup("#status").setVisible(visibility);
        pane.lookup("#editButton").setVisible(visibility);
        pane.lookup("#requestButton").setVisible(visibility);
        pane.lookup("#quantity").setVisible(visibility);
        pane.lookup("#descriptionHbox").setVisible(visibility);
        pane.lookup("#rating").setVisible(visibility);
        pane.lookup("#offersButton").setVisible(visibility);

        pane.lookup("#nameTextField").setVisible(!visibility);
        pane.lookup("#priceTextField").setVisible(!visibility);
        pane.lookup("#discountedPriceTextField").setVisible(!visibility);
        pane.lookup("#statusTextField").setVisible(!visibility);
        pane.lookup("#descriptionTextField").setVisible(!visibility);
        pane.lookup("#saveButton").setVisible(!visibility);
    }

    @FXML
    protected void logout(ActionEvent event) throws IOException {
        LoggedInUser.getInstance().setUser(null);
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("login.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }

    @FXML
    public void addProduct(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(ShoppingPointApplication.class.getResource("add_product.fxml"));
        ((Node)actionEvent.getSource()).getScene().setRoot(loader.load());
    }

    @FXML
    public void goToSummary(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(ShoppingPointApplication.class.getResource("summary.fxml"));
        ((Node)actionEvent.getSource()).getScene().setRoot(loader.load());
    }
}
