package com.wisedodge.purescalculator;

import com.wisedodge.purescalculator.ui.PuresCalculatorUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class  PuresCalculatorApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pures Calculator");

        PuresCalculatorUI calculatorUI = new PuresCalculatorUI();
        Scene scene = new Scene(calculatorUI.getGrid(), 400, 450);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}