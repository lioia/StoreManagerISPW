package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.controller.OffersController;
import com.example.shoppingpoint.controller.SendEmailController;
import com.example.shoppingpoint.dao.UserDAO;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.model.Offer;
import com.example.shoppingpoint.model.Request;
import com.example.shoppingpoint.singleton.LoggedInUser;
import com.example.shoppingpoint.utils.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

import static com.example.shoppingpoint.utils.ExceptionHandler.CONTROLLER_HEADER_TEXT;

public class OffersGraphicController {
    @FXML
    private Label productNameText;
    @FXML
    private FlowPane requestsPane;

    private final OffersController controller;

    public OffersGraphicController() {
        controller = new OffersController();
    }

    public void initialize(GenericProduct product) throws IOException {
        try {
            productNameText.setText(product.getName() + " Offers - Shopping Point");

            List<Request> requests = controller.getRequestsOfProduct(product.getId());

            for (Request req : requests) {
                FXMLLoader reqLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("reusable/request.fxml"));
                VBox node = reqLoader.load();
                ((Label) node.lookup("#requestIdLabel")).setText("Request: " + req.getRequestId());
                ((Label) node.lookup("#maxPriceLabel")).setText("Max Price: " + req.getMaxPrice() + "€");
                ((Label) node.lookup("#quantityLabel")).setText("Quantity: " + req.getQuantity());

                if (req.isAccepted()) {
                    Offer acceptedOffer = controller.getAcceptedOffer(req.getRequestId());
                    ((Label) node.lookup("#statusLabel")).setText(String.format("Accepted offer: %.02f by %s", acceptedOffer.getOfferPrice(), acceptedOffer.getSupplierUsername()));
                } else {
                    showOffers(node, req);
                }
                requestsPane.getChildren().add(node);
            }
        } catch (ControllerException e) {
            ExceptionHandler.handleException(CONTROLLER_HEADER_TEXT, e.getMessage());
        }
    }

    private void showOffers(VBox parentNode, Request req) throws IOException, ControllerException {
        ((Label) parentNode.lookup("#statusLabel")).setText("Not accepted");
        List<Offer> offers = controller.getOffersOfRequest(req.getRequestId());
        if (offers.size() > 0) {
            FlowPane offersPane = (FlowPane) parentNode.lookup("#offersPane");
            FXMLLoader offerLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("reusable/offer.fxml"));
            Pane offer = offerLoader.load();
            for (Offer off : offers) {
                ((Label) offer.lookup("#supplierNameLabel")).setText(off.getSupplierUsername());
                ((Label) offer.lookup("#offerPriceLabel")).setText(String.format("Offer price: %.02f€", off.getOfferPrice()));
                Text sendEmail = (Text) offer.lookup("#sendEmail");
                sendEmail.setOnMouseClicked(event -> {
                    try {
                        String supplier = off.getSupplierUsername();
                        new SendEmailController().sendEmail(UserDAO.getEmailByUsername(supplier));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                ((Button) offer.lookup("#acceptButton")).setOnAction(event -> {
                    try {
                        controller.acceptOffer(req, off.getOfferId());
                        goBack(event);
                    } catch (ControllerException e) {
                        ExceptionHandler.handleException(CONTROLLER_HEADER_TEXT, e.getMessage());
                    } catch (IOException e) {
                        ExceptionHandler.handleException("Could not go back", e.getMessage());
                    }
                });
            }
            offersPane.getChildren().add(offer);
        }
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(ShoppingPointApplication.class.getResource("store_dashboard.fxml"));
        ((Node) actionEvent.getSource()).getScene().setRoot(loader.load());
    }

    @FXML
    protected void logout(ActionEvent event) throws IOException {
        LoggedInUser.getInstance().setUser(null);
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("login.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }
}
