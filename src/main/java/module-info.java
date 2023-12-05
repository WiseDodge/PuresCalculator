module com.example.purescalculator {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens com.wisedodge.purescalculator to javafx.fxml;
    exports com.wisedodge.purescalculator;
    exports com.wisedodge.purescalculator.ui;
    opens com.wisedodge.purescalculator.ui to javafx.fxml;
    exports com.wisedodge.purescalculator.converters;
    opens com.wisedodge.purescalculator.converters to javafx.fxml;
}