package com.wisedodge.purescalculator.lookupmodule;

import com.wisedodge.purescalculator.logging.DevLogger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PitPandaPureCounter {

    public static Map<String, Integer> countItems(JSONObject pitPandaResponse) {
        Map<String, Integer> counts = new HashMap<>();

        JSONObject inventoriesObject = pitPandaResponse.getJSONObject("data").getJSONObject("inventories");

        // Iterate over inventories and items
        for (String inventory : inventoriesObject.keySet()) {
            JSONArray itemsArray = inventoriesObject.getJSONArray(inventory);

            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject item = itemsArray.getJSONObject(i);

                if (item.has("name")) {
                    String itemName = item.getString("name");
                    int itemCount = item.getInt("count");

                    // Customize this part based on the item names in your use case
                    switch (itemName) {
                        case "§5Chunk of Vile":
                            counts.put("vile", counts.getOrDefault("vile", 0) + itemCount);
                            break;
                        case "§3Funky Feather":
                            counts.put("feathers", counts.getOrDefault("feathers", 0) + itemCount);
                            break;
                        case "§aPhilosopher's Cactus":
                            counts.put("cactus", counts.getOrDefault("cactus", 0) + itemCount);
                            break;
                        case "§bPants Bundle":
                            if (item.getJSONArray("desc").getString(item.getJSONArray("desc").length() - 1).equals("open")) {
                                counts.put("pants", counts.getOrDefault("pants", 0) + 10);
                            }
                            break;
                        case "§eMystic Sword":
                            counts.put("swords", counts.getOrDefault("swords", 0) + itemCount);
                            break;
                        case "§bMystic Bow":
                            counts.put("bows", counts.getOrDefault("bows", 0) + itemCount);
                            break;
                        case "§dMini Cake":
                            counts.put("minicake", counts.getOrDefault("minicake", 0) + itemCount);
                            break;
                        // Add more cases for other items
                    }

                    // Logging item count
                    DevLogger.logItemCount("Item: %s, Count: %d%n", itemName, itemCount);
                }
            }
        }

        return counts;
    }

    public static double calculateTotalNetWorth(Map<String, Integer> itemCounts) {
        // Conversion rates
        double feathersToVileRate = 10.0;
        double cactusToVileRate = 3.0;
        double pantsToVileRate = 2.0;
        double swordsToVileRate = 3.0;
        double bowsToVileRate = 2.0;
        double minicakeToVileRate = 6.0;
        double pantsBundleToVileRate = 18.0;

        // Calculate total net worth in Vile
        int vileCount = itemCounts.getOrDefault("vile", 0);
        int feathersCount = itemCounts.getOrDefault("feathers", 0);
        int cactusCount = itemCounts.getOrDefault("cactus", 0);
        int pantsCount = itemCounts.getOrDefault("pants", 0);
        int swordsCount = itemCounts.getOrDefault("swords", 0);
        int bowsCount = itemCounts.getOrDefault("bows", 0);
        int minicakeCount = itemCounts.getOrDefault("minicake", 0);

        double totalNetWorthInVile = (vileCount +
                                      feathersCount * feathersToVileRate +
                                      cactusCount * cactusToVileRate +
                                      pantsCount * pantsToVileRate +
                                      swordsCount * swordsToVileRate +
                                      bowsCount * bowsToVileRate +
                                      minicakeCount * minicakeToVileRate);

        // Convert to Pants Bundles (pbs) and round to 2 decimal places
        double totalNetWorthInPures = Math.round(totalNetWorthInVile / pantsBundleToVileRate * 100.0) / 100.0;

        // Logging total net worth
        DevLogger.logTotalNetworth("Total Net Worth in Pures: %.2f%n", totalNetWorthInPures);

        return totalNetWorthInPures;
    }
}