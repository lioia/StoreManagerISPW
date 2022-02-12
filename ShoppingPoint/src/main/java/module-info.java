module com.example.shoppingpoint {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.material2;
    requires java.sql;
    requires mysql.connector.java;
    requires org.controlsfx.controls;
    requires org.jsoup;
    requires java.desktop;

    opens com.shoppingpoint to javafx.fxml;
    opens com.shoppingpoint.graphic_controller to javafx.fxml;
    exports com.shoppingpoint;
    exports com.shoppingpoint.graphic_controller;
    exports com.shoppingpoint.model;
    exports com.shoppingpoint.model.user;
    exports com.shoppingpoint.model.product;
    exports com.shoppingpoint.adapter;
    exports com.shoppingpoint.bean;
    exports com.shoppingpoint.bean.store_dashboard;
    exports com.shoppingpoint.bean.add_product;
    exports com.shoppingpoint.utils;
    exports com.shoppingpoint.exception;
    exports com.shoppingpoint.controller;
}

