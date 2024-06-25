package com.wisedodge.purescalculator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PuresCalculatorApp extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public final void start(final Stage primaryStage) {
    primaryStage.setTitle("Pures Calculator");

    com.wisedodge.purescalculator.ui.puresCalculatorUI
            calculatorUI = new com.wisedodge.purescalculator.ui.puresCalculatorUI();
    Scene scene = new Scene(calculatorUI.getGrid(), 400, 450);

    primaryStage.setScene(scene);
    primaryStage.show();
  }
}