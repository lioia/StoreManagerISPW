package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.bean.SearchStoreBean;
import com.example.shoppingpoint.controller.PaymentController;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.model.Store;
import com.example.shoppingpoint.singleton.LoggedInUser;
import com.example.shoppingpoint.utils.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

import static com.example.shoppingpoint.utils.ExceptionHandler.CONTROLLER_HEADER_TEXT;

public class SearchStoreGraphicController {

    @FXML
    private TextField searchTextField;
    @FXML
    private ToggleGroup toggle;
    @FXML
    private FlowPane storesPane;

    //    This method gets called when loading the view
    @FXML
    public void initialize() throws IOException {
//        Create stores view with all the value in the database
        createStorePaneView(new SearchStoreBean());
    }

    @FXML
    public void search() throws IOException {
        String filter = ((RadioButton) toggle.getSelectedToggle()).getText();
        String searchQuery = searchTextField.getText();

        createStorePaneView(new SearchStoreBean(filter, searchQuery));
    }

    private void createStorePaneView(SearchStoreBean bean) throws IOException {
        try {
            storesPane.getChildren().clear();
            PaymentController controller = new PaymentController();
            List<Store> stores = controller.getStores(bean);

            for (Store store : stores) {
                FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("reusable/store_pane.fxml"));
                AnchorPane node = fxmlLoader.load();
                ((Text) node.lookup("#storeName")).setText(store.getName());
                ((Text) node.lookup("#storeAddress")).setText(store.getAddress());
                ((Text) node.lookup("#storeType")).setText("Type: " + store.getType().name().toLowerCase());
                ((Button) node.lookup("#navigateButton")).setOnAction((ActionEvent event) -> {
                    try {
                        navigateToStore(event, store);
                    } catch (IOException e) {
                        ExceptionHandler.handleException("Could not go to next screen", e.getMessage());
                    }
                });
                storesPane.getChildren().add(node);
            }
        } catch (ControllerException e) {
            ExceptionHandler.handleException(CONTROLLER_HEADER_TEXT, e.getMessage());
        }
    }

    private void navigateToStore(ActionEvent event, Store store) throws IOException {
//      Load store fxml
        FXMLLoader storeFxml = new FXMLLoader(ShoppingPointApplication.class.getResource("store.fxml"));
        Parent storeNode = storeFxml.load();
//      Navigate to store
        ((Node) event.getSource()).getScene().setRoot(storeNode);
//      Get the graphic controller from the store page
        StoreGraphicController storeController = storeFxml.getController();
//      Set the store on the controller and initialize data
        storeController.initialize(store);
    }

    @FXML
    public void filter(ActionEvent event) throws IOException {
        String filter = ((RadioButton) event.getSource()).getText();
        createStorePaneView(new SearchStoreBean(filter, ""));
    }

    @FXML
    protected void logout(ActionEvent event) throws IOException {
        LoggedInUser.getInstance().setUser(null);
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("login.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }
}
