package com.wisedodge.purescalculator.lookupmodule.koslist;

import com.wisedodge.purescalculator.lookupmodule.PitPandaApiAccessor;
import com.wisedodge.purescalculator.logging.DevLogger;
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
public class KosList {
    private final Map<String, PlayerStatus> playerStatusMap = new HashMap<>();
    private static final KosList instance = new KosList();

    // Logger for static block
    private static final DevLogger STATIC_BLOCK_LOGGER = DevLogger.getInstance();

    // Logger for constructor
    private static final DevLogger CONSTRUCTOR_LOGGER = DevLogger.getInstance();

    static {
        STATIC_BLOCK_LOGGER.loggingKosList("KosList class is being initialized.");
    }

    private KosList() {
        CONSTRUCTOR_LOGGER.loggingKosList("KosList instance created.");

        // Time schedule to update player status every 1 minute
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this::updatePlayerStatuses, 0, 1, TimeUnit.MINUTES);
    }

    public static KosList getInstance() {
        return instance;
    }

    private void updatePlayerStatuses() {
        DevLogger.loggingKosList("Updating player statuses...");

        for (String playerName : playerStatusMap.keySet()) {
            try {
                DevLogger.loggingKosList("Calling API for player: {}", playerName);
                JSONObject playerData = PitPandaApiAccessor.getPlayerData(playerName);

                if (playerData != null) {
                    boolean isOnline = playerData.optBoolean("online", false);
                    ZonedDateTime lastInPit = parseLastInPit(playerData.optString("lastinpit", ""));

                    PlayerStatus currentStatus = playerStatusMap.getOrDefault(playerName, new PlayerStatus(false, null));
                    PlayerStatus newStatus = new PlayerStatus(isOnline, lastInPit);

                    if (!currentStatus.equals(newStatus)) {
                        // Update the status and notify the UI
                        playerStatusMap.put(playerName, newStatus);
                        KosListUI.updatePlayerStatus(playerName, newStatus);
                    }
                } else {
                    DevLogger.loggingKosList("Player data is null for playerName: {}", playerName);
                }
            } catch (Exception e) {
                DevLogger.loggingKosList("Error updating player status for playerName: {}", playerName, e);
            }
        }
    }

    public void addPlayer(String playerName) {
        playerStatusMap.put(playerName, new PlayerStatus(false, null));
    }

    public boolean isPlayerOnline(String playerName) {
        PlayerStatus playerStatus = playerStatusMap.get(playerName);
        return playerStatus != null && playerStatus.isOnline();
    }

    public ZonedDateTime getLastInPit(String playerName) {
        PlayerStatus playerStatus = playerStatusMap.get(playerName);
        return playerStatus != null ? playerStatus.lastInPit() : null;
    }

    public void importKosListFromFile(Path filePath) {
        try {
            List<String> playerNames = Files.readAllLines(filePath);
            for (String playerName : playerNames) {
                addPlayer(playerName.trim());
            }
        } catch (IOException e) {
            DevLogger.loggingKosList("Error importing Kos list from file: {}", filePath, e);
        }
    }

    private ZonedDateTime parseLastInPit(String lastInPitString) {
        if (!lastInPitString.isEmpty()) {
            return ZonedDateTime.parse(lastInPitString, DateTimeFormatter.ISO_DATE_TIME);
        }
        return null;
    }
}
