package com.example.shoppingpoint.utils;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import org.controlsfx.control.PopOver;

public class DescriptionHandler {
    private DescriptionHandler() {
        throw new IllegalStateException();
    }

    public static void showDescription(ActionEvent event, String description) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMaxWidth(400.0);
        scrollPane.setMaxHeight(400.0);
        scrollPane.setPadding(new Insets(16));
        Label label = new Label();
        label.setText(description);
        label.setStyle("-fx-font-size: 16px");
        label.setMaxWidth(350.0);
        label.setWrapText(true);
        scrollPane.setContent(label);
        PopOver popOver = new PopOver();
        Node node = (Node) event.getSource();
        popOver.setArrowLocation(PopOver.ArrowLocation.TOP_LEFT);
        popOver.setContentNode(scrollPane);
        popOver.setCornerRadius(16);
        popOver.show(node);
    }
}
