module PuresCalculator {
    requires org.json;
    requires javafx.graphics;
    requires javafx.controls;

    opens com.wisedodge.purescalculator to javafx.fxml;
    exports com.wisedodge.purescalculator;
    exports com.wisedodge.purescalculator.ui;
    opens com.wisedodge.purescalculator.ui to javafx.fxml;
    exports com.wisedodge.purescalculator.converters;
    opens com.wisedodge.purescalculator.converters to javafx.fxml;

}