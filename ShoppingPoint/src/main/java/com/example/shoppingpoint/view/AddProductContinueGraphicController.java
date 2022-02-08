package com.example.shoppingpoint.view;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.bean.add_product.AddProductBean;
import com.example.shoppingpoint.bean.add_product.AddProductCommonBean;
import com.example.shoppingpoint.controller.AddProductController;
import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.singleton.LoggedInUser;
import com.example.shoppingpoint.utils.ExceptionHandler;
import com.example.shoppingpoint.utils.ProductType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.IOException;

import static com.example.shoppingpoint.utils.ExceptionHandler.BEAN_HEADER_TEXT;
import static com.example.shoppingpoint.utils.ExceptionHandler.CONTROLLER_HEADER_TEXT;

public class AddProductContinueGraphicController {
    private AddProductCommonBean commonBean;
    private ProductType type;

    @FXML
    private VBox pane;

    @FXML
    public void initialize(AddProductCommonBean bean, ProductType type) {
        this.commonBean = bean;
        this.type = type;

        String[] consoles = {"PS5", "PS4", "Xbox Series X", "Xbox Series S", "Xbox One", "Nintendo Switch"};

        switch (type) {
            case CLOTHES -> pane.getChildren().addAll(createTextFieldElement("Size"), createTextFieldElement("Material"));
            case SHOES -> pane.getChildren().addAll(createTextFieldElement("Size"), createTextFieldElement("Material"), createTextFieldElement("Type"));
            case BOOK -> pane.getChildren().addAll(createTextFieldElement("Author"), createTextAreaElement("Plot"), createTextFieldElement("Genre"));
            case COMICS -> pane.getChildren().addAll(createTextFieldElement("Author"), createTextFieldElement("Artist"), createTextAreaElement("Plot"), createTextFieldElement("Genre"), createTextFieldElement("Volume"));
            case VIDEOGAME -> pane.getChildren().addAll(createTextAreaElement("Plot"), createTextFieldElement("Genre"), createComboBoxElement(consoles));
            case GAMECONSOLE -> pane.getChildren().addAll(createComboBoxElement(consoles), createCheckBoxElement());
            case COMPUTER -> {
                String[] elements = {"Laptop", "Desktop", "2in1", "Touch Screen"};
                pane.getChildren().addAll(createComboBoxElement(elements), createTextFieldElement("RAM"), createTextFieldElement("SSD"), createTextFieldElement("Battery"), createTextFieldElement("CPU"), createTextFieldElement("GPU"), createTextFieldElement("Brand"), createTextFieldElement("Display"));
            }
            case HOMEAPPLIANCES -> pane.getChildren().addAll(createTextFieldElement("Energy Class"), createTextAreaElement("Specs"));
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    @FXML
    public void add(ActionEvent actionEvent) {
        try {
            AddProductBean bean = new AddProductBean();
            switch (type) {
                case CLOTHES -> {
                    bean.setSize(((TextField) pane.lookup("#Size")).getText());
                    bean.setMaterial(((TextField) pane.lookup("#Material")).getText());
                }
                case SHOES -> {
                    bean.setSize(((TextField) pane.lookup("#Size")).getText());
                    bean.setMaterial(((TextField) pane.lookup("#Material")).getText());
                    bean.setShoesType(((TextField) pane.lookup("#Type")).getText());
                }
                case BOOK -> {
                    bean.setAuthor(((TextField) pane.lookup("#Author")).getText());
                    bean.setGenre(((TextField) pane.lookup("#Genre")).getText());
                    bean.setPlot(((TextArea) pane.lookup("#Plot")).getText());
                }
                case COMICS -> {
                    bean.setAuthor(((TextField) pane.lookup("#Author")).getText());
                    bean.setArtist(((TextField) pane.lookup("#Artist")).getText());
                    bean.setGenre(((TextField) pane.lookup("#Genre")).getText());
                    bean.setPlot(((TextArea) pane.lookup("#Plot")).getText());
                    bean.setVolumeNumber(Integer.parseInt(((TextField) pane.lookup("#Volume")).getText()));
                }
                case VIDEOGAME -> {
                    bean.setGenre(((TextField) pane.lookup("#Genre")).getText());
                    bean.setPlot(((TextArea) pane.lookup("#Plot")).getText());
                    bean.setConsoleType(((ComboBox<String>) pane.lookup("#Type")).getValue());
                }
                case GAMECONSOLE -> {
                    bean.setConsoleType(((ComboBox<String>) pane.lookup("#Type")).getValue());
                    bean.setDigitalOnly(((CheckBox) pane.lookup("#Digital")).isSelected());
                }
                case COMPUTER -> {
                    bean.setComputerType(((ComboBox<String>) pane.lookup("#Type")).getValue());
                    bean.setRam(Integer.parseInt(((TextField) pane.lookup("#RAM")).getText()));
                    bean.setSsd(Integer.parseInt(((TextField) pane.lookup("#SSD")).getText()));
                    bean.setCpu(((TextField) pane.lookup("#CPU")).getText());
                    bean.setGpu(((TextField) pane.lookup("#GPU")).getText());
                    bean.setBatterySize(Integer.parseInt(((TextField) pane.lookup("#Battery")).getText()));
                    bean.setDisplaySize(Float.parseFloat(((TextField) pane.lookup("#Display")).getText()));
                    bean.setBrand(((TextField) pane.lookup("#Brand")).getText());
                }
                case HOMEAPPLIANCES -> {
                    bean.setEnergyClass(((TextField) pane.lookup("#Energy Class")).getText());
                    bean.setSpecs(((TextField) pane.lookup("#Specs")).getText());
                }
                default -> throw new IllegalStateException("Unexpected value: " + type);
            }
            AddProductController controller = new AddProductController();
            controller.saveProduct(type, bean, commonBean);
            FXMLLoader loader = new FXMLLoader(ShoppingPointApplication.class.getResource("store_dashboard.fxml"));
            ((Node) actionEvent.getSource()).getScene().setRoot(loader.load());
        } catch (BeanException e) {
            ExceptionHandler.handleException(BEAN_HEADER_TEXT, e.getMessage());
        } catch (Exception e) {
            ExceptionHandler.handleException(CONTROLLER_HEADER_TEXT, e.getMessage());
        }
    }

    private HBox createTextFieldElement(String name) {
        HBox hbox = new HBox(40);
        Label label = new Label(name);
        label.setFont(new Font(24));
        TextField textField = new TextField();
        textField.setId(name);
        textField.setPrefSize(384, 40);
        textField.setStyle("-fx-background-color: WHITE; -fx-background-radius: 12px");
        textField.setEffect(new DropShadow());
        hbox.getChildren().addAll(label, textField);
        return hbox;
    }

    private HBox createTextAreaElement(String name) {
        HBox hbox = new HBox(40);
        Label label = new Label(name);
        label.setFont(new Font(24));
        TextArea textArea = new TextArea();
        textArea.setId(name);
        textArea.setPrefSize(384, 40);
        textArea.setStyle("-fx-background-color: WHITE; -fx-background-radius: 12px");
        textArea.setEffect(new DropShadow());
        hbox.getChildren().addAll(label, textArea);
        return hbox;
    }

    private HBox createComboBoxElement(String[] elements) {
        HBox hbox = new HBox(40);
        Label label = new Label("Type");
        label.setFont(new Font(24));
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setId("Type");
        comboBox.setPrefSize(384, 40);
        comboBox.setStyle("-fx-background-radius: WHITE;");
        comboBox.setEffect(new DropShadow());
        comboBox.getItems().addAll(elements);
        hbox.getChildren().addAll(label, comboBox);
        return hbox;
    }

    private HBox createCheckBoxElement() {
        HBox hbox = new HBox(4);
        Label label = new Label("Digital");
        label.setFont(new Font(24));
        CheckBox box = new CheckBox();
        box.setId("Digital");
        hbox.getChildren().addAll(label, box);
        return hbox;
    }

    @FXML
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
