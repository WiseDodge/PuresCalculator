package com.wisedodge.purescalculator.lookupmodule;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.JSONObject;
import java.util.Map;

public class PlayerLookupUI {

    public static void openPlayerLookupScreen() {
        // New stage for the player lookup screen
        Stage playerLookupStage = new Stage();
        playerLookupStage.setTitle("Player Lookup");
        playerLookupStage.initModality(Modality.APPLICATION_MODAL);

        // Layout for player lookup screen
        VBox playerLookupLayout = new VBox(10);

        // Component Buttons for player lookup screen
        Label lookupLabel = new Label("Look up player: ");
        TextField lookupField = new TextField();
        Button searchButton = new Button("Search");

        // Label to display results
        Label resultLabel = new Label();

        //  Event handler for the search button
        searchButton.setOnAction(actionEvent -> {
            String playerName = lookupField.getText();
            // Get the player data
            JSONObject pitPandaResponse = PitPandaApiAccessor.getPlayerData(playerName);

            // Check if the player data is not null and success is true
            if (pitPandaResponse != null && pitPandaResponse.getBoolean("success")) {
                // Perform further processing with the player data
                Map<String, Integer> itemCounts = PitPandaPureCounter.countItems(pitPandaResponse);

                // Display item counts and total net worth in the label
                StringBuilder resultText = new StringBuilder("Pures for " + playerName + ":\n");
                for (Map.Entry<String, Integer> entry : itemCounts.entrySet()) {
                    resultText.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                }

                double totalNetWorth = PitPandaPureCounter.calculateTotalNetWorth(itemCounts);
                resultText.append("Total Net Worth in Pures: ").append(totalNetWorth);

                // Set the result text to the label
                resultLabel.setText(resultText.toString());
            } else {
                // Set an error message if the player is not found or has not played Pit
                resultLabel.setText("Player not found or has not played Pit.");
            }
        });

        // Add UI components to the layout
        playerLookupLayout.getChildren().addAll(lookupLabel, lookupField, searchButton, resultLabel);
        playerLookupLayout.setPadding(new Insets(10, 10, 10, 10));
        playerLookupLayout.setMinHeight(300); // Set a fixed height for the VBox layout

        // Button to return to the calculator
        Button returnToCalculatorButton = new Button("Return to Calculator");
        returnToCalculatorButton.setOnAction(e -> playerLookupStage.close());

        // Add the return to calculator button to the layout
        playerLookupLayout.getChildren().add(returnToCalculatorButton);

        // Scene for the player lookup stage
        Scene playerLookupScene = new Scene(playerLookupLayout, 400, 300); // Set width and height for the scene
        playerLookupStage.setScene(playerLookupScene);

        // display player lookup stage
        playerLookupStage.show();


    }
}