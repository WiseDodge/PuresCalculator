package com.example.purescalculator.ui;

import com.example.purescalculator.converters.PbToVileConverter;
import com.example.purescalculator.converters.VileToPbConverter;
import com.example.purescalculator.lookupmodule.PlayerLookupUI;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class VileCalculatorUI {
    private GridPane grid;
    private TextField quantityInput;
    private ChoiceBox<String> conversionTypeChoice;
    private Label resultLabel;

    public VileCalculatorUI() {
        initializeUI();
    }

    private void initializeUI() {
        grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

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
        GridPane.setConstraints(lookupPlayerButton,1,3);

        resultLabel = new Label();
        GridPane.setConstraints(resultLabel, 0, 3, 2, 1);

        Label infoLabel = new Label("Standard Market Prices for Pures:\n" +
                "1 Pant Bundle = 10 Fresh Pants or 18 Vile\n" +
                "1 Fresh Pant = 2 Vile\n" +
                "1 Philosopher's Cactus = 2/2.5/3 Vile \n" +
                "1 Funky Feather = 10 Vile\n" +
                "1 Fresh Mystic Sword = 2-6 Vile\n" +
                "1 Fresh Mystic Bow = 1-4 Vile\n" +
                "1 MiniCake = 6 Vile");
        GridPane.setConstraints(infoLabel, 0, 4, 2, 1);

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
                    result = String.format("%.1f Vile is equivalent to %.1f PB", quantity, new VileToPbConverter().convert(quantity));
                } else {
                    result = "Invalid conversion type.";
                }

                resultLabel.setText(result);

            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input. Please enter a valid number.");
            }
        });

        // Event Handler for the lookup player button
        lookupPlayerButton.setOnAction(e -> PlayerLookupUI.openPlayerLookupScreen());

        // Components added to the grid
        grid.getChildren().addAll(quantityInput, conversionTypeChoice, calculateButton, lookupPlayerButton, resultLabel, infoLabel);
    }




public GridPane getGrid() {

        return grid;
    }

}