package com.shoppingpoint.graphic_controller;

import com.shoppingpoint.ShoppingPointApplication;
import com.shoppingpoint.bean.RequestListBean;
import com.shoppingpoint.controller.MakeOfferController;
import com.shoppingpoint.controller.SendEmailController;
import com.shoppingpoint.exception.BeanException;
import com.shoppingpoint.exception.ControllerException;
import com.shoppingpoint.model.Request;
import com.shoppingpoint.singleton.LoggedInUser;
import com.shoppingpoint.utils.DescriptionHandler;
import com.shoppingpoint.utils.ExceptionHandler;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;

public class RequestListGraphicController {
    @FXML
    private FlowPane requestPane;

    @FXML
    private Label offersAccepted;

    @FXML
    public void initialize() throws IOException {
        try {
            createRequestPaneView();
            MakeOfferController controller = new MakeOfferController();
            int newAcceptedOffer = controller.countAcceptedOffers();
            if (newAcceptedOffer != 0) {
                offersAccepted.setText(String.format("Hanno accettato %d offerte", newAcceptedOffer));
                offersAccepted.setVisible(true);
                PauseTransition transition = new PauseTransition(Duration.seconds(3));
                transition.setOnFinished(event -> offersAccepted.setVisible(false));
                transition.play();
            }
        } catch (ControllerException e) {
            ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT, e.getMessage());
        }
    }

    private void createRequestPaneView() throws IOException {
        try {
            requestPane.getChildren().clear();
            MakeOfferController controller = new MakeOfferController();
            List<Request> requestList = controller.getRequest();
            for (Request request : requestList) {
                FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("reusable/request_product_pane.fxml"));
                AnchorPane node = fxmlLoader.load();

                ((Text) node.lookup("#ProductName")).setText(request.getProduct().getName());
                ((Text) node.lookup("#quantity")).setText(String.format("Quantity: %d", request.getQuantity()));
                ((Text) node.lookup("#maxPrice")).setText(String.format("Max Price:%.2f", request.getMaxPrice()));
                ((Label) node.lookup("#description")).setText(request.getProduct().getDescription());
                ((Text) node.lookup("#store")).setText("Store: " + request.getProduct().getStoreName());
                ((Button) node.lookup("#descriptionButton")).setOnAction((ActionEvent event) -> DescriptionHandler.showDescription(event, request.getProduct().getDescription()));
                Text sendEmail = (Text) node.lookup("#sendEmail");
                sendEmail.setOnMouseClicked(event -> {
                    try {
                        SendEmailController emailController = new SendEmailController();
                        String username = emailController.getUsernameOfStore(request.getProduct().getStoreName());
                        emailController.sendEmail(username);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                ((Button) node.lookup("#offerButton")).setOnAction((ActionEvent event) -> {
                    try {
                        controller.saveOffer(request.getRequestId(), new RequestListBean(((TextField) node.lookup("#choosePriceTextField")).getText()));
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Offer successfully sent");
                        alert.show();
                    } catch (BeanException e) {
                        ExceptionHandler.handleException(ExceptionHandler.BEAN_HEADER_TEXT, e.getMessage());
                    } catch (ControllerException e) {
                        ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT, e.getMessage());
                    }
                });
                requestPane.getChildren().add(node);
            }
        } catch (ControllerException e) {
            ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT, e.getMessage());
        }
    }

    @FXML
    protected void logout(ActionEvent event) throws IOException {
        LoggedInUser.getInstance().setUser(null);
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("login.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }

    @FXML
    private void goAcceptedOffer(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader acceptedOffersFxml = new FXMLLoader(ShoppingPointApplication.class.getResource("AcceptedOffers.fxml"));
            Parent acceptedOffersNode = acceptedOffersFxml.load();
            ((Node) actionEvent.getSource()).getScene().setRoot(acceptedOffersNode);
            AcceptedOffersGraphicController acceptedOffersGraphicController = acceptedOffersFxml.getController();
            acceptedOffersGraphicController.initialize();
            MakeOfferController controller = new MakeOfferController();
            controller.checkedOffer();
        } catch (ControllerException e) {
            ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT, e.getMessage());
        }
    }
}
