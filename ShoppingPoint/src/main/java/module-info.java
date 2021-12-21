module com.example.shoppingpoint {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.shoppingpoint to javafx.fxml;
    exports com.example.shoppingpoint;
}