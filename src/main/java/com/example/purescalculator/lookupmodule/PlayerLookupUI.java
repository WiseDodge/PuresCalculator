package com.example.purescalculator.lookupmodule;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

        // Event handler for the search button
        searchButton.setOnAction(actionEvent -> {
            String playerName = lookupField.getText();
            // logic for player search through the given name goes here
            System.out.println("Searching for player: " + playerName);
        });

        // Add UI components to the layout
        playerLookupLayout.getChildren().addAll(lookupLabel, lookupField, searchButton);
        playerLookupLayout.setPadding(new Insets(10, 10, 10, 10));

        Button backButton = new Button("Back to Converter");
        backButton.setOnAction(e -> playerLookupStage.close());

        // adds the back button to the layout
        playerLookupLayout.getChildren().add(backButton);

        // Scene for the player lookup stage
        Scene playerLookupScene = new Scene(playerLookupLayout, 300, 100);
        playerLookupStage.setScene(playerLookupScene);

        // display player lookup stage
        playerLookupStage.show();
    }



}
