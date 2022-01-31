package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.bean.store_dashboard.EditProductBean;
import com.example.shoppingpoint.bean.store_dashboard.LoyaltyCardBean;
import com.example.shoppingpoint.controller.StoreDashboardController;
import com.example.shoppingpoint.exception.BeanException;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;


import com.example.shoppingpoint.model.user.*;
import com.example.shoppingpoint.model.Store;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.Rating;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class StoreDashboardGraphicController {

    private final StoreDashboardController controller;

    @FXML
    private Label labelStoreName;
    @FXML
    private FlowPane productsPane;

    public StoreDashboardGraphicController() {
        controller = new StoreDashboardController();
    }

    @FXML
    public void initialize() throws IOException {
        try {
            Store store = controller.getStoreFromStoreOwnerName(LoggedInUser.getInstance().getUser().getUsername());
            ((StoreOwner) LoggedInUser.getInstance().getUser()).setStore(store);
            labelStoreName.setText(((StoreOwner) LoggedInUser.getInstance().getUser()).getStore().getName() + " - Shopping Point");
        } catch(Exception e) { // TODO handle controller exception
            e.printStackTrace();
        }
        createProductsView(((StoreOwner) LoggedInUser.getInstance().getUser()).getStore());
    }

    @FXML
    public void goToClientList(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("client_list.fxml"));
        Parent node = fxmlLoader.load();
        ((Node) event.getSource()).getScene().setRoot(node);
        ClientListGraphicController clientListGraphicController = fxmlLoader.getController();
        clientListGraphicController.initialize();
    }

    private void createProductsView(Store store) {
        try {
            productsPane.getChildren().clear();
            List<GenericProduct> products = controller.getProductsFromStore(store);

            for (GenericProduct product : products) {
                FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("reusable/store_dashboard_product_pane.fxml"));
                float reviewAverage = controller.getReviewOfProduct(product.getId());
                AnchorPane pane = fxmlLoader.load();
//            Set product data in the View
                ((Label) pane.lookup("#name")).setText(product.getName());
                String formattedPrice = String.format("%.02f€", product.getPrice()); // Price with 2 decimal points
                ((Label) pane.lookup("#price")).setText("Price: " + formattedPrice);
                ((TextField) pane.lookup("#priceTextField")).setText(product.getPrice().toString());
                String formattedDiscountedPrice = String.format("%.02f€", product.getDiscountedPrice()); // Price with 2 decimal points
                ((Label) pane.lookup("#discountedPrice")).setText("Discounted Price: " + formattedDiscountedPrice);
                ((TextField) pane.lookup("#discountedPriceTextField")).setText(product.getDiscountedPrice().toString());
                ((Label) pane.lookup("#status")).setText(product.getStatus());
                ((Label) pane.lookup("#description")).setText(product.getDescription());
                ((Label) pane.lookup("#quantity")).setText(String.format("Quantity: %d", product.getQuantity()));
                ((TextField) pane.lookup("#quantityTextField")).setText(product.getQuantity().toString());
                ((Rating) pane.lookup("#rating")).setRating(reviewAverage);
                ((ImageView) pane.lookup("#imageView")).setImage(product.getImage());
                ((Button) pane.lookup("#descriptionButtonOfLabel")).setOnAction(event -> {
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
                        newRequestGraphicController.initialize(product);
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
                    ((Button) pane.lookup("#uploadImageButton")).setOnAction((ActionEvent uploadEvent) -> {
                        try {
                            FileChooser chooser = new FileChooser();
                            //Set extension filter
                            FileChooser.ExtensionFilter extFilterJPG
                                    = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
                            FileChooser.ExtensionFilter extFilterjpg
                                    = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
                            FileChooser.ExtensionFilter extFilterPNG
                                    = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
                            FileChooser.ExtensionFilter extFilterpng
                                    = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
                            chooser.getExtensionFilters()
                                    .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
                            File image = chooser.showOpenDialog(null);
                            if (image != null) {
                                controller.setImageOfProduct(product.getId(), image);
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setContentText("Correctly uploaded file: " + image.getName());
                                alert.show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                    ((Button) pane.lookup("#saveButton")).setOnAction((ActionEvent actionEvent) -> {
                        setProductVisibility(pane, true);
                        String price = ((TextField) pane.lookup("#priceTextField")).getText();
                        String discountedPrice = ((TextField) pane.lookup("#discountedPriceTextField")).getText();
                        String quantity = ((TextField) pane.lookup("#quantityTextField")).getText();
                        try {
                            EditProductBean bean = new EditProductBean(price, discountedPrice, quantity);
                            controller.editProduct(product.getId(), bean);
//                        Update local copy of product (and the relative labels)
                            product.setPrice(bean.getPrice());
                            product.setDiscountedPrice(bean.getDiscountedPrice());
                            product.setQuantity(bean.getQuantity());
                            ((Label) pane.lookup("#price")).setText(String.format("Price: %.02f€", product.getPrice()));
                            ((Label) pane.lookup("#discountedPrice")).setText(String.format("Discounted Price: %.02f€", product.getDiscountedPrice()));
                            ((Label) pane.lookup("#quantity")).setText(String.format("Quantity: %d", product.getQuantity()));
                        } catch (BeanException e) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText("Incorrect data");
                            alert.setContentText(e.getMessage());
                            alert.show();
                        } catch (Exception e) { // TODO handle controller exception
                            e.printStackTrace();
                        }
                    });

                });
//            Add product to the view
                productsPane.getChildren().add(pane);
            }
        } catch (Exception e) { // TODO handle controller exception
            e.printStackTrace();
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
            } catch (BeanException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Incorrect data");
                alert.setContentText(e.getMessage());
                alert.show();
            } catch (Exception e) { // TODO handle controller exception
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
        pane.lookup("#price").setVisible(visibility);
        pane.lookup("#discountedPrice").setVisible(visibility);
        pane.lookup("#status").setVisible(visibility);
        pane.lookup("#editButton").setVisible(visibility);
        pane.lookup("#requestButton").setVisible(visibility);
        pane.lookup("#quantity").setVisible(visibility);
        pane.lookup("#descriptionHbox").setVisible(visibility);
        pane.lookup("#rating").setVisible(visibility);
        pane.lookup("#offersButton").setVisible(visibility);

        pane.lookup("#priceTextField").setVisible(!visibility);
        pane.lookup("#discountedPriceTextField").setVisible(!visibility);
        pane.lookup("#quantityTextField").setVisible(!visibility);
        pane.lookup("#saveButton").setVisible(!visibility);
        pane.lookup("#uploadImageButton").setVisible(!visibility);
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
        ((Node) actionEvent.getSource()).getScene().setRoot(loader.load());
    }

    @FXML
    public void goToSummary(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(ShoppingPointApplication.class.getResource("summary.fxml"));
        ((Node) actionEvent.getSource()).getScene().setRoot(loader.load());
    }
}
