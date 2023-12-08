package com.wisedodge.purescalculator.lookupmodule;

import org.json.JSONObject;

import java.util.Map;

public class PlayerLookup {
    public static void searchPlayer(String playerName) {
        // Call the PitPanda API to get player data
        JSONObject pitPandaResponse = PitPandaApiAccessor.getPlayerData(playerName);

        // Check if the player data is not null and success is true
        if (pitPandaResponse != null && pitPandaResponse.getBoolean("success")) {
            // Perform further processing with the player data
            Map<String, Integer> itemCounts = PitPandaPureCounter.countItems(pitPandaResponse);

            // Display item counts and total net worth
            System.out.println("Pures for " + playerName + ":");
            for (Map.Entry<String, Integer> entry : itemCounts.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            double totalNetWorth = PitPandaPureCounter.calculateTotalNetWorth(itemCounts);
            System.out.println("Total Net Worth in Pures: " + totalNetWorth);
        } else {
            System.out.println("Player not found or has not played Pit.");
        }
    }

    public static void main(String[] args) {
        PlayerLookupUI.openPlayerLookupScreen();
    }
}