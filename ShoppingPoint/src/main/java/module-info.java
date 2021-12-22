module com.example.shoppingpoint {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.material2;

    opens com.example.shoppingpoint to javafx.fxml;
    opens com.example.shoppingpoint.view to javafx.fxml;
    exports com.example.shoppingpoint;
    exports com.example.shoppingpoint.view;
}