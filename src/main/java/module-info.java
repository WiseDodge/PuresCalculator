module com.example.purescalculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.purescalculator to javafx.fxml;
    exports com.example.purescalculator;
    exports com.example.purescalculator.ui;
    opens com.example.purescalculator.ui to javafx.fxml;
    exports com.example.purescalculator.converters;
    opens com.example.purescalculator.converters to javafx.fxml;
}