package com.wisedodge.purescalculator.lookupmodule.koslist;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class kosListUi {

  private static final Map<String, Label> playerStatusLabels = new HashMap<>();
  private static final Stage kosListStage = new Stage();

  public static void showKosList() {
    Stage kosListStage = new Stage();
    kosListStage.setTitle("Kos LIST (smoke da opps)");
    kosListStage.initModality(Modality.APPLICATION_MODAL);

    VBox kosListLayout = new VBox(10);
    Label lookupLabel = new Label("Look up player: ");
    TextField lookupField = new TextField();
    Button searchButton = new Button("Search");
    Button importButton = new Button("Import Kos List");
    Button returnToCalculatorButton = new Button("Return to Calculator");

    // Event handler for the search button
    searchButton.setOnAction(actionEvent -> {
      String playerName = lookupField.getText().trim();
      // Calls method to check if player is online
      boolean isOnline = kosList.getInstance().isPlayerOnline(playerName);
      System.out.println(playerName + " Online Status: " + (isOnline ? "Online" : "Offline"));
      updatePlayerStatus(playerName, new playerStatus(isOnline, kosList.getInstance().getLastInPit(playerName)));
    });

    // Event handler for the import button
    importButton.setOnAction(actionEvent -> {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Select Kos List File");
      File file = fileChooser.showOpenDialog(kosListStage);

      if (file != null) {
        Path filePath = file.toPath();
        kosList.getInstance().importKosListFromFile(filePath);
        System.out.println("Kos List imported from file: " + filePath);
      }
    });

    // Event handler for the return to calculator button
    returnToCalculatorButton.setOnAction(actionEvent -> kosListStage.close());

    kosListLayout.getChildren().addAll(lookupLabel, lookupField, searchButton, importButton, returnToCalculatorButton);

    kosListStage.setScene(new Scene(kosListLayout, 300, 200));
    kosListStage.show();
  }

  public static void updatePlayerStatus(String playerName, playerStatus playerStatus) {
    if (kosListStage.isShowing()) {
      System.out.println("Updating player status for: " + playerName);
      if (playerStatusLabels.containsKey(playerName)) {
        Label statusLabel = playerStatusLabels.get(playerName);
        statusLabel.setText(formatPlayerStatus(playerName, playerStatus));
      } else {
        Label statusLabel = new Label(formatPlayerStatus(playerName, playerStatus));
        playerStatusLabels.put(playerName, statusLabel);
        ((VBox) kosListStage.getScene().getRoot()).getChildren().add(statusLabel);
      }
    } else {
      System.out.println("kosListStage is not showing");
    }
  }

  private static String formatPlayerStatus(String playerName, playerStatus playerStatus) {
    String status = playerName + " Online Status: " + (playerStatus.isOnline() ? "Online" : "Offline");
    if (playerStatus.lastInPit() != null) {
      status += ", Last In Pit: " + playerStatus.lastInPit();
    }
    return status;
  }
}
