package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.singleton.LoggedInUser;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import com.example.shoppingpoint.controller.RequestListController;
import com.example.shoppingpoint.adapter.ProductAdapter;
import com.example.shoppingpoint.bean.RequestListBean;
import com.example.shoppingpoint.controller.SendEmailController;
import com.example.shoppingpoint.dao.StoreDAO;
import com.example.shoppingpoint.dao.UserDAO;
import com.example.shoppingpoint.dao.OfferDAO;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.example.shoppingpoint.model.Request;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.controlsfx.control.PopOver;

public class RequestListGraphicController {
    @FXML
    private FlowPane requestPane;

    @FXML
    private Label offersAccepted;
    @FXML
    public void initialize() throws Exception {
        createRequestPaneView();
        int newAcceptedOffer = OfferDAO.countAcceptedOffer(LoggedInUser.getInstance().getUser().getUsername());
        if (newAcceptedOffer != 0) {
            offersAccepted.setText(String.format("Hanno accettato %d offerte", newAcceptedOffer));
            offersAccepted.setVisible(true);
            PauseTransition transition = new PauseTransition(Duration.seconds(3));
            transition.setOnFinished(event -> offersAccepted.setVisible(false));
            transition.play();

        }
    }

    private void createRequestPaneView() {
        try {
            requestPane.getChildren().clear();
            RequestListController controller = new RequestListController();
            List<Request> requestList = controller.getRequest();
            for (Request request : requestList) {
                FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("reusable/request_product_pane.fxml"));
                AnchorPane node = fxmlLoader.load();
                GenericProduct product = new ProductAdapter(controller.getProduct(request.getProductId()));

                ((Text) node.lookup("#ProductName")).setText(product.getName());
                ((Text) node.lookup("#quantity")).setText(String.format("Quantity: %d", request.getQuantity()));
                ((Text) node.lookup("#maxPrice")).setText(String.format("Max Price:%.2f", request.getMaxPrice()));
                ((Label) node.lookup("#description")).setText(product.getDescription());
                ((Text) node.lookup("#store")).setText("Store: " + product.getStoreName());
                ((Button) node.lookup("#descriptionButton")).setOnAction((ActionEvent event) -> {
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
                    Node pane = (Node) event.getSource();
                    popOver.setArrowLocation(PopOver.ArrowLocation.TOP_LEFT);
                    popOver.setContentNode(scrollPane);
                    popOver.setCornerRadius(16);
                    popOver.show(pane);
                });
                Text sendEmail = (Text) node.lookup("#sendEmail");
                sendEmail.setOnMouseClicked(event -> {
                    try {
                        String storeOwner = StoreDAO.getStoreOwnerUsernameByStoreName(product.getStoreName());
                        new SendEmailController().sendEmail(UserDAO.getEmailByUsername(storeOwner));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                ((Button) node.lookup("#offerButton")).setOnAction((ActionEvent event) -> {
                    try {
                        controller.saveOffer(request.getRequestId(), new RequestListBean(((TextField) node.lookup("#choosePriceTextField")).getText()));
                    } catch (BeanException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Incorrect data");
                        alert.setContentText(e.getMessage());
                        alert.show();
                    } catch (Exception e) { // TODO handle controller exception
                        e.printStackTrace();
                    }
                });
                requestPane.getChildren().add(node);
            }
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

    @FXML
    private void goAcceptedOffer(ActionEvent actionEvent) throws Exception{
        FXMLLoader acceptedOffersFxml = new FXMLLoader(ShoppingPointApplication.class.getResource("AcceptedOffers.fxml"));
        Parent acceptedOffersNode = acceptedOffersFxml.load();

        ((Node) actionEvent.getSource()).getScene().setRoot(acceptedOffersNode);

        AcceptedOffersGraphicController acceptedOffersGraphicController = acceptedOffersFxml.getController();

        acceptedOffersGraphicController.initialize();
        try {
            RequestListController controller = new RequestListController();
            controller.checkedOffer();
        } catch (SQLException e) { // TODO handle exception
            e.printStackTrace();
        }

    }
}
