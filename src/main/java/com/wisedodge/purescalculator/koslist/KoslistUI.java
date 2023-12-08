package com.wisedodge.purescalculator.koslist;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class KoslistUI {

    public static void killlookupscreen() {

        Stage killstage = new Stage();
        killstage.setTitle("Kos LIST (smoke da opps)");
        killstage.initModality(Modality.APPLICATION_MODAL);

        VBox killlookup = new VBox(10);
        Label lookupLabel = new Label("Look up player: ");
        TextField lookupField = new TextField();
        Button searchButton = new Button("Search");

    }
}
