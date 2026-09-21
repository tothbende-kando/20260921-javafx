module com.example.listak {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.listak to javafx.fxml;
    exports com.example.listak;
}