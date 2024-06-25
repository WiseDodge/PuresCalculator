package com.wisedodge.purescalculator.lookupmodule;

import java.util.Map;
import org.json.JSONObject;

public class playerLookup {
  public static void searchPlayer(String playerName) {
    // Call the PitPanda API to get player data
    JSONObject pitPandaResponse = pitPandaApiAccessor.getPlayerData(playerName);

    // Check if the player data is not null and success is true
    if (pitPandaResponse != null && pitPandaResponse.getBoolean("success")) {
      // Perform further processing with the player data
      Map<String, Integer> itemCounts = pitPandaPureCounter.countItems(pitPandaResponse);

      // Display item counts and total net worth
      System.out.println("Pures for " + playerName + ":");
      for (Map.Entry<String, Integer> entry : itemCounts.entrySet()) {
        System.out.println(entry.getKey() + ": " + entry.getValue());
      }

      double totalNetWorth = pitPandaPureCounter.calculateTotalNetWorth(itemCounts);
      System.out.println("Total Net Worth in Pures: " + totalNetWorth);
    } else {
      System.out.println("Player not found or has not played Pit.");
    }
  }

  public static void main(String[] args) {
    playerLookupUI.openPlayerLookupScreen();
  }
}