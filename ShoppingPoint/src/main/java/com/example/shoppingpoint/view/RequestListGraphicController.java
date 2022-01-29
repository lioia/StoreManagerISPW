package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.model.user.Supplier;
import com.example.shoppingpoint.singleton.LoggedInUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import com.example.shoppingpoint.controller.RequestListController;
import com.example.shoppingpoint.adapter.ProductAdapter;
import com.example.shoppingpoint.bean.RequestListBean;

import java.io.IOException;
import java.util.List;

import com.example.shoppingpoint.model.Request;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import org.controlsfx.control.PopOver;

public class RequestListGraphicController {
    @FXML
    private FlowPane requestPane;

    @FXML
    public void initialize() throws Exception {
        createRequestPaneView();
    }

    private void createRequestPaneView() throws Exception {
        requestPane.getChildren().clear();
        RequestListController controller = new RequestListController();
        List<Request> requestList = controller.getRequest();
        for (Request request : requestList) {
            FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("reusable/request_product_pane.fxml"));
            AnchorPane node = fxmlLoader.load();
            //TODO è giusto cosi product adapter?
            ProductAdapter product = new ProductAdapter(controller.getProduct(request.getProductId()));

            ((Text) node.lookup("#ProductName")).setText(product.getName());
            ((Text) node.lookup("#quantity")).setText(String.format("Quantity: %d", request.getQuantity()));
            ((Text) node.lookup("#maxPrice")).setText(String.format("Max Price:%.2f", request.getMaxPrice()));
            ((Label) node.lookup("#description")).setText(product.getDescription());
            ((Text) node.lookup("#store")).setText("Store: "+product.getStore());
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
            ((Button) node.lookup("#offerButton")).setOnAction((ActionEvent event)  -> {
                try {
                    controller.saveOffer(product.getId(), new RequestListBean( ((TextField) node.lookup("#choosePriceTextField")).getText()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });



            requestPane.getChildren().add(node);
        }
    }

    @FXML
    protected void logout(ActionEvent event) throws IOException {
        LoggedInUser.getInstance().setUser(null);
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("login.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }
}
