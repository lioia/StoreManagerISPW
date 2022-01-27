package com.example.shoppingpoint.view;

import com.example.shoppingpoint.bean.add_product.AddProductBean;
import com.example.shoppingpoint.bean.add_product.AddProductCommonBean;
import com.example.shoppingpoint.controller.AddProductController;
import com.example.shoppingpoint.utils.ProductType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class AddProductContinueGraphicController {
    private AddProductCommonBean commonBean;
    private ProductType type;

    @FXML
    private VBox pane;

    @FXML
    public void initialize(AddProductCommonBean bean, ProductType type) throws Exception {
        this.commonBean = bean;
        this.type = type;

        switch (type) {
            case CLOTHES -> {
                pane.getChildren().addAll(createTextFieldElement("Size"), createTextFieldElement("Material"));
            }
            case SHOES -> {
                pane.getChildren().addAll(createTextFieldElement("Size"), createTextFieldElement("Material"), createTextFieldElement("Type"));
            }
            case BOOK -> {
                pane.getChildren().addAll(createTextFieldElement("Author"), createTextAreaElement("Plot"), createTextFieldElement("Genre"));
            }
            case COMICS -> {
                pane.getChildren().addAll(createTextFieldElement("Author"), createTextFieldElement("Artist"), createTextAreaElement("Plot"), createTextFieldElement("Genre"), createTextFieldElement("Volume"));
            }
            case VIDEOGAME -> {
                String[] elements = {"PS5", "PS4", "XBox Series X", "XBox Series S", "XBox One", "Nintendo Switch"};
                pane.getChildren().addAll(createTextAreaElement("Plot"), createTextFieldElement("Genre"), createComboBoxElement("Type", elements));
            }
            case GAMECONSOLE -> {
                String[] elements = {"PS5", "PS4", "XBox Series X", "XBox Series S", "XBox One", "Nintendo Switch"};
                pane.getChildren().addAll(createComboBoxElement("Type", elements), createCheckBoxElement("Digital"));
            }
            case COMPUTER -> {
                String[] elements = {"Laptop", "Desktop", "2in1", "Touch Screen"};
                pane.getChildren().addAll(createComboBoxElement("Type", elements), createTextFieldElement("RAM"), createTextFieldElement("SSD"), createTextFieldElement("Battery"), createTextFieldElement("CPU"), createTextFieldElement("GPU"), createTextFieldElement("Brand"), createTextFieldElement("Display"));
            }
            case HOMEAPPLIANCES -> {
                pane.getChildren().addAll(createTextFieldElement("Energy Class"), createTextAreaElement("Specs"));
            }
            default -> throw new Exception("Unsupported type");
        }
    }

    @FXML
    public void add(ActionEvent actionEvent) throws Exception {
        String size = null;
        String material = null;
        String shoesType = null;
        String author = null;
        String artist = null;
        String plot = null;
        String genre = null;
        String volumeNumber = null;
        String consoleType = null;
        boolean digitalOnly = false;
        String computerType = null;
        String ram = null;
        String ssd = null;
        String cpu = null;
        String gpu = null;
        String batterySize = null;
        String displaySize = null;
        String brand = null;
        String energyClass = null;
        String specs = null;

        switch (type) {
            case CLOTHES -> {
                size = ((TextField) pane.lookup("Size")).getText();
                material = ((TextField) pane.lookup("Material")).getText();
            }
            case SHOES -> {
                size = ((TextField) pane.lookup("Size")).getText();
                material = ((TextField) pane.lookup("Material")).getText();
                shoesType = ((TextField) pane.lookup("Type")).getText();
            }
            case BOOK -> {
                author = ((TextField) pane.lookup("Author")).getText();
                genre = ((TextField) pane.lookup("Genre")).getText();
                plot = ((TextArea) pane.lookup("Plot")).getText();
            }
            case COMICS -> {
                author = ((TextField) pane.lookup("Author")).getText();
                artist = ((TextField) pane.lookup("Artist")).getText();
                genre = ((TextField) pane.lookup("Genre")).getText();
                plot = ((TextArea) pane.lookup("Plot")).getText();
                volumeNumber = ((TextField) pane.lookup("Volume")).getText();
            }
            case VIDEOGAME -> {
                genre = ((TextField) pane.lookup("Genre")).getText();
                plot = ((TextArea) pane.lookup("Plot")).getText();
                consoleType = ((ComboBox<String>) pane.lookup("Type")).getValue();
            }
            case GAMECONSOLE -> {
                consoleType = ((ComboBox<String>) pane.lookup("Type")).getValue();
                digitalOnly = ((CheckBox) pane.lookup("Digital")).isSelected();
            }
            case COMPUTER -> {
                computerType = ((TextField) pane.lookup("Type")).getText();
                ram = ((TextField) pane.lookup("RAM")).getText();
                ssd = ((TextField) pane.lookup("SSD")).getText();
                cpu = ((TextArea) pane.lookup("CPU")).getText();
                gpu = ((TextField) pane.lookup("GPU")).getText();
                batterySize = ((TextField) pane.lookup("Battery")).getText();
                displaySize = ((TextField) pane.lookup("Display")).getText();
                brand = ((TextField) pane.lookup("Brand")).getText();
            }
            case HOMEAPPLIANCES -> {
                energyClass = ((TextField) pane.lookup("Energy Class")).getText();
                specs = ((TextField) pane.lookup("Specs")).getText();
            }
        }
        AddProductBean bean = new AddProductBean(type, size, material, shoesType, author, artist, plot, genre, volumeNumber, consoleType, digitalOnly, computerType, ram, ssd, cpu, gpu, batterySize, displaySize, brand, energyClass, specs);
        AddProductController controller = new AddProductController();
        controller.saveProduct(type, bean, commonBean);
    }

    private HBox createTextFieldElement(String name) {
        HBox hbox = new HBox(8);
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
        HBox hbox = new HBox(8);
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

    private HBox createComboBoxElement(String name, String[] elements) {
        HBox hbox = new HBox(8);
        Label label = new Label(name);
        label.setFont(new Font(24));
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setId(name);
        comboBox.setPrefSize(384, 40);
        comboBox.setStyle("-fx-background-radius: WHITE; -fx-background-radius: 12px");
        comboBox.setEffect(new DropShadow());
        comboBox.getItems().addAll(elements);
        hbox.getChildren().addAll(label, comboBox);
        return hbox;
    }

    private HBox createCheckBoxElement(String name) {
        HBox hbox = new HBox(8);
        Label label = new Label(name);
        label.setFont(new Font(24));
        CheckBox box = new CheckBox();
        box.setId(name);
        hbox.getChildren().addAll(label, box);
        return hbox;
    }
}
