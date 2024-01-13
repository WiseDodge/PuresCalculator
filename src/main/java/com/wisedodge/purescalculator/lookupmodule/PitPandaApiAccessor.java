package com.wisedodge.purescalculator.lookupmodule;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class PitPandaApiAccessor {

    private static final String BASE_URL = "https://pitpanda.rocks/api/players/";
    private static final Logger LOGGER = LogManager.getLogger(PitPandaApiAccessor.class);

    public static JSONObject getPlayerData(String playerName) {
        HttpURLConnection connection = null;

        try {
            // Construct the API URL
            String apiUrl = BASE_URL + playerName;

            // Create a URL object
            URL url = createUrl(apiUrl);

            // Opens a connection to the URL
            connection = (HttpURLConnection) url.openConnection();

            // Set the request method
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();

            // Logging the API request and response code
            LOGGER.info("API Request - URL: {}, Response Code: {}", apiUrl, responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder responseStringBuilder = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        responseStringBuilder.append(line);
                    }

                    // Parse the JSON response
                    return new JSONObject(responseStringBuilder.toString());
                }
            } else if (responseCode == HttpURLConnection.HTTP_FORBIDDEN) {
                LOGGER.error("Access forbidden. Check your API key.");
            } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
                LOGGER.error("Player not found or has not played Pit.");
            } else if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST) {
                LOGGER.error("Request limit reached. Consider rate limiting your requests.");
            } else {
                LOGGER.error("Error: Unable to retrieve data. Response Code: {}", responseCode);
            }

        } catch (IOException e) {
            // Logging API request failure
            LOGGER.error("Error accessing PitPanda API: {}", e.getMessage());
        } finally {
            // Close the connection in a finally block to ensure it's closed regardless of exceptions
            if (connection != null) {
                connection.disconnect();
            }
        }

        return null;
    }

    private static URL createUrl(String apiUrl) throws MalformedURLException {
        try {
            return new URI(apiUrl).toURL();
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
