package com.example.shoppingpoint.view;
import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.model.user.Supplier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import com.example.shoppingpoint.controller.RequestListController;
import com.example.shoppingpoint.model.product.Product;
import java.io.IOException;
import java.util.List;
import com.example.shoppingpoint.model.Request;
import javafx.scene.text.Text;

public class RequestListGraphicController {
    private Supplier supplier;

    @FXML
    private FlowPane requestPane;

    @FXML
    public void initialize(Supplier supplier) throws Exception{
        this.supplier=supplier;
        createRequestPaneView();
    }

    private void createRequestPaneView() throws Exception{
        requestPane.getChildren().clear();
        RequestListController controller = new RequestListController();
        List<Request> requestList = controller.getRequest();
        for(Request request : requestList){
            FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("reusable/request_product_pane.fxml"));
            AnchorPane node = fxmlLoader.load();
            Product product = controller.getProduct(request.getProductId());

            ((Text) node.lookup("#ProductName")).setText(product.getName());
            ((Text) node.lookup("#quantity")).setText(String.format("Quantity: %d",request.getQuantity()));
            ((Text) node.lookup("#maxPrice")).setText(String.format("Max Price:%.2f",request.getMaxPrice()));

            requestPane.getChildren().add(node);
        }

    }

    @FXML
    protected void logout(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("login.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }
}
