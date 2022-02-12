package com.shoppingpoint.graphic_controller;

import com.shoppingpoint.ShoppingPointApplication;
import com.shoppingpoint.controller.AcceptedOfferController;
import com.shoppingpoint.controller.SendEmailController;
import com.shoppingpoint.exception.ControllerException;
import com.shoppingpoint.exception.EmailException;
import com.shoppingpoint.model.Offer;
import com.shoppingpoint.singleton.LoggedInUser;
import com.shoppingpoint.utils.DescriptionHandler;
import com.shoppingpoint.utils.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

public class AcceptedOffersGraphicController {

    @FXML
    FlowPane acceptedOfferPane;

    @FXML
    public void initialize() throws IOException {
        createAcceptedOfferPaneView();
    }

    private void createAcceptedOfferPaneView() throws IOException {
        try {
            acceptedOfferPane.getChildren().clear();
            AcceptedOfferController controller = new AcceptedOfferController();
            List<Offer> acceptedOffersList = controller.getAcceptedOffersOfSupplier();
            for (Offer acceptedOffer : acceptedOffersList) {
                FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("reusable/request_product_pane.fxml"));
                AnchorPane node = fxmlLoader.load();
                ((Text) node.lookup("#ProductName")).setText(acceptedOffer.getRequest().getProduct().getName());
                ((Text) node.lookup("#quantity")).setText(String.format("Quantity: %d", acceptedOffer.getRequest().getQuantity()));
                ((Text) node.lookup("#maxPrice")).setText(String.format("Offer Price:%.2f", acceptedOffer.getOfferPrice()));
                ((Label) node.lookup("#description")).setText(acceptedOffer.getRequest().getProduct().getDescription());
                ((Text) node.lookup("#store")).setText("Store: " + acceptedOffer.getRequest().getProduct().getStoreName());
                ((Button) node.lookup("#descriptionButton")).setOnAction((ActionEvent event) -> DescriptionHandler.showDescription(event, acceptedOffer.getRequest().getProduct().getDescription()));
                Text sendEmail = (Text) node.lookup("#sendEmail");
                sendEmail.setOnMouseClicked(event -> {
                    try {
                        SendEmailController emailController = new SendEmailController();
                        String username = emailController.getUsernameOfStore(acceptedOffer.getRequest().getProduct().getStoreName());
                        emailController.sendEmail(username);
                    } catch (ControllerException e) {
                        ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT, e.getMessage());
                    } catch (EmailException e) {
                        ExceptionHandler.handleException("Email", e.getMessage());
                    }
                });
                if (!controller.isOfferChecked(acceptedOffer.getOfferId())) {
                    (node.lookup("#newAcceptedOffer")).setVisible(true);
                }
                (node.lookup("#offerButton")).setVisible(false);
                (node.lookup("#choosePriceHBox")).setVisible(false);
                acceptedOfferPane.getChildren().add(node);

            }
        } catch (ControllerException e) {
            ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT, e.getMessage());
        }
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("request_list.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }

    @FXML
    public void logout(ActionEvent event) throws IOException {
        LoggedInUser.getInstance().setUser(null);
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("login.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }
}
