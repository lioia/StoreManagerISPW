package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.controller.OffersController;
import com.example.shoppingpoint.model.Offer;
import com.example.shoppingpoint.model.Request;
import com.example.shoppingpoint.model.user.StoreOwner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class OffersGraphicController {
    private StoreOwner storeOwner;

    @FXML
    private Label productNameText;
    @FXML
    private FlowPane requestsPane;

    public void initData(StoreOwner storeOwner, GenericProduct product) throws Exception {
        OffersController controller = new OffersController();
        this.storeOwner = storeOwner;
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
                ((Label) node.lookup("#statusLabel")).setText(String.format("Accepted offer: %.02f by %s", acceptedOffer.getOfferPrice(), acceptedOffer.getSupplier().getUsername()));
            } else {
                ((Label) node.lookup("#statusLabel")).setText("Not accepted");
                List<Offer> offers = controller.getOffersOfRequest(req.getRequestId());
                if (offers.size() > 0) {
                    FlowPane offersPane = (FlowPane) node.lookup("#offersPane");
                    FXMLLoader offerLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("reusable/offer.fxml"));
                    Pane offer = offerLoader.load();
                    for (Offer off : offers) {
                        ((Label) offer.lookup("#supplierNameLabel")).setText(off.getSupplier().getUsername());
                        ((Label) offer.lookup("#offerPriceLabel")).setText(String.format("Offer price: %.02f€", off.getOfferPrice()));
                        ((Button) offer.lookup("#acceptButton")).setOnAction(event -> {
                            try {
                                controller.acceptOffer(req, product, off.getOfferId());
                                goBack(event);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    }
                    offersPane.getChildren().add(offer);
                }
            }
            requestsPane.getChildren().add(node);
        }
    }

    public void goBack(ActionEvent actionEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader(ShoppingPointApplication.class.getResource("store_dashboard.fxml"));
        Parent node = loader.load();
        ((Node) actionEvent.getSource()).getScene().setRoot(node);
        StoreDashboardGraphicController storeDashboardGraphicController = loader.getController();
        storeDashboardGraphicController.initData(storeOwner);
    }

    @FXML
    protected void logout(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("Login.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }
}
