module com.example.shoppingpoint {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.material2;
    requires java.sql;
    requires mysql.connector.java;
    requires org.controlsfx.controls;

    opens com.example.shoppingpoint to javafx.fxml;
    opens com.example.shoppingpoint.view to javafx.fxml;
    exports com.example.shoppingpoint;
    exports com.example.shoppingpoint.view;
    exports com.example.shoppingpoint.model;
    exports com.example.shoppingpoint.model.user;
    exports com.example.shoppingpoint.model.product;
    exports com.example.shoppingpoint.adapter;
    exports com.example.shoppingpoint.bean.store_dashboard;
}
