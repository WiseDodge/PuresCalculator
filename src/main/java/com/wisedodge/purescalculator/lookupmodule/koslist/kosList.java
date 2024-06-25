package com.wisedodge.purescalculator.lookupmodule.koslist;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// ! still unresolved, cant figure out why it's not showing the right status
public class kosList {
  private static final kosList instance = new kosList();

  static {
    com.wisedodge.purescalculator.logging.devLogger.loggingKosList("kosList class is being initialized.");
  }

  private final Map<String, playerStatus> playerStatusMap = new HashMap<>();

  private kosList() {
    com.wisedodge.purescalculator.logging.devLogger.loggingKosList("kosList instance created.");

    // Time schedule to update player status every 1 minute
    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    executorService.scheduleAtFixedRate(this::updatePlayerStatuses, 0, 1, TimeUnit.MINUTES);
  }

  public static kosList getInstance() {
    return instance;
  }

  private void updatePlayerStatuses() {
    com.wisedodge.purescalculator.logging.devLogger.loggingKosList("Updating player statuses...");

    for (String playerName : playerStatusMap.keySet()) {
      try {
        com.wisedodge.purescalculator.logging.devLogger.loggingKosList("Calling API for player: {}", playerName);
        JSONObject playerData = com.wisedodge.purescalculator.lookupmodule.pitPandaApiAccessor.getPlayerData(playerName);

        if (playerData != null) {
          boolean isOnline = playerData.optBoolean("online", false);
          ZonedDateTime lastInPit = parseLastInPit(playerData.optString("lastinpit", ""));

          playerStatus currentStatus = playerStatusMap.getOrDefault(playerName,
                  new playerStatus(false,
                          null));
          playerStatus newStatus = new playerStatus(isOnline, lastInPit);

          if (!currentStatus.equals(newStatus)) {
            // Update the status and notify the UI
            playerStatusMap.put(playerName, newStatus);
            kosListUi.updatePlayerStatus(playerName, newStatus);
          }
        } else {
          com.wisedodge.purescalculator.logging.devLogger.loggingKosList("Player data is null for playerName: {}", playerName);
        }
      } catch (Exception e) {
        com.wisedodge.purescalculator.logging.devLogger.loggingKosList("Error updating player status for playerName: {}", playerName, e);
      }
    }
  }

  public void addPlayer(String playerName) {
    playerStatusMap.put(playerName, new playerStatus(false, null));
  }

  public boolean isPlayerOnline(String playerName) {
    playerStatus playerStatus = playerStatusMap.get(playerName);
    return playerStatus != null && playerStatus.isOnline();
  }

  public ZonedDateTime getLastInPit(String playerName) {
    playerStatus playerStatus = playerStatusMap.get(playerName);
    return playerStatus != null ? playerStatus.lastInPit() : null;
  }

  public void importKosListFromFile(Path filePath) {
    try {
      List<String> playerNames = Files.readAllLines(filePath);
      for (String playerName : playerNames) {
        addPlayer(playerName.trim());
      }
    } catch (IOException e) {
      com.wisedodge.purescalculator.logging.devLogger.loggingKosList("Error importing Kos list from file: {}", filePath, e);
    }
  }

  private ZonedDateTime parseLastInPit(String lastInPitString) {
    if (!lastInPitString.isEmpty()) {
      return ZonedDateTime.parse(lastInPitString, DateTimeFormatter.ISO_DATE_TIME);
    }
    return null;
  }
}
