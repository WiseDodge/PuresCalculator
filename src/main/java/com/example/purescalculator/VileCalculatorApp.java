package com.example.purescalculator;

import com.example.purescalculator.ui.VileCalculatorUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VileCalculatorApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Vile Calculator");

        VileCalculatorUI calculatorUI = new VileCalculatorUI();
        Scene scene = new Scene(calculatorUI.getGrid(), 400, 450);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}