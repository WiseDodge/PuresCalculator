package com.wisedodge.purescalculator.ui;

import com.wisedodge.purescalculator.converters.PbToVileConverter;
import com.wisedodge.purescalculator.converters.VileToPbConverter;
import com.wisedodge.purescalculator.lookupmodule.koslist.KosListUI;
import com.wisedodge.purescalculator.lookupmodule.PlayerLookupUI;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PuresCalculatorUI {
    private GridPane grid;
    private TextField quantityInput;
    private ChoiceBox<String> conversionTypeChoice;
    private Label resultLabel;

    public PuresCalculatorUI() {
        initializeUI();
    }

    private void initializeUI() {
        grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Set a minimum width for the entire GridPane
        grid.setMinWidth(500);

        quantityInput = new TextField();
        GridPane.setConstraints(new Label("Enter Quantity:"), 0, 0);
        GridPane.setConstraints(quantityInput, 1, 0);

        conversionTypeChoice = new ChoiceBox<>();
        conversionTypeChoice.getItems().addAll("PB to Vile", "Vile to PB");
        conversionTypeChoice.setValue("PB to Vile");
        GridPane.setConstraints(new Label("Select Conversion Type:"), 0, 1);
        GridPane.setConstraints(conversionTypeChoice, 1, 1);

        Button calculateButton = new Button("Calculate");
        GridPane.setConstraints(calculateButton, 1, 2);

        Button lookupPlayerButton = new Button("Look up player");
        GridPane.setConstraints(lookupPlayerButton, 1, 3);

        Button kosListButton = new Button("Kos List");
        GridPane.setConstraints(kosListButton, 1, 4);

        resultLabel = new Label();
        GridPane.setConstraints(resultLabel, 2, 1, 1, 2);

        Label infoLabel = new Label("""
                Standard Market Prices for Pures:
                1 Pant Bundle = 10 Pants or 18 Vile
                1 Fresh Pant = 2 Vile
                1 Philosopher's Cactus = 2/2.5/3 Vile\s
                1 Funky Feather = 10 Vile
                1 Fresh Mystic Sword = 2-6 Vile
                1 Fresh Mystic Bow = 1-4 Vile
                1 MiniCake = 6 Vile""");
        GridPane.setConstraints(infoLabel, 0, 5, 2, 1);

        // Event handler for the button click
        calculateButton.setOnAction(e -> {
            try {
                double quantity = Double.parseDouble(quantityInput.getText());
                String conversionType = conversionTypeChoice.getValue();

                // Delegate the calculation to the appropriate converter
                String result;
                if (conversionType.equals("PB to Vile")) {
                    result = new PbToVileConverter().convert(quantity);
                } else if (conversionType.equals("Vile to PB")) {
                    result = new VileToPbConverter().convert(quantity);
                } else {
                    result = "Invalid conversion type.";
                }

                resultLabel.setText(result);

                // Move the resultLabel to a different column
                GridPane.setColumnIndex(resultLabel, 2);

            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input. Please enter a valid number.");
            }
        });

        // Event Handler for the lookup player button
        lookupPlayerButton.setOnAction(e -> PlayerLookupUI.openPlayerLookupScreen());

        // Event Handler for the Kos List button
        kosListButton.setOnAction(e -> KosListUI.showKosList());

        // Components added to the grid
        grid.getChildren().addAll(quantityInput, conversionTypeChoice, calculateButton, lookupPlayerButton, kosListButton, resultLabel, infoLabel);
    }

    public GridPane getGrid() {
        return grid;
    }
}
