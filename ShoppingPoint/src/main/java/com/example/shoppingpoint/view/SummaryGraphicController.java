package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.bean.SummaryBean;
import com.example.shoppingpoint.controller.SummaryController;
import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.model.SoldProduct;
import com.example.shoppingpoint.singleton.LoggedInUser;
import com.example.shoppingpoint.utils.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SummaryGraphicController {

    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private ToggleGroup toggle;

    @FXML
    public void initialize() {
        createChart(((RadioButton) toggle.getSelectedToggle()).getText());
    }

    @FXML
    public void filter(ActionEvent actionEvent) {
        createChart(((RadioButton) actionEvent.getSource()).getText());
    }

    private void createChart(String selected) {
        try {
            barChart.getData().clear();
            SummaryController controller = new SummaryController();
            HashMap<String, List<SoldProduct>> products = controller.getSoldProducts(new SummaryBean(selected));
            for (String key : products.keySet()) {
                XYChart.Series<String, Integer> series = new XYChart.Series<>();
                series.setName(key);
                for (SoldProduct p : products.get(key)) {
                    series.getData().add(new XYChart.Data<>(p.getDate().toString(), p.getQuantity()));
                }
                barChart.getData().add(series);
            }
        } catch (BeanException e) {
            ExceptionHandler.handleException(BEAN_HEADER_TEXT, e.getMessage());
        } catch (ControllerException e) {
            ExceptionHandler.handleException(CONTROLLER_HEADER_TEXT, e.getMessage());
        }
    }

    @FXML
    public void goBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(ShoppingPointApplication.class.getResource("store_dashboard.fxml"));
        ((Node) actionEvent.getSource()).getScene().setRoot(loader.load());
    }

    @FXML
    public void logout(ActionEvent actionEvent) throws IOException {
        LoggedInUser.getInstance().setUser(null);
        FXMLLoader loader = new FXMLLoader(ShoppingPointApplication.class.getResource("login.fxml"));
        ((Node) actionEvent.getSource()).getScene().setRoot(loader.load());
    }
}
