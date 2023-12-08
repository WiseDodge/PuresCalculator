package com.wisedodge.purescalculator.logging;

public class DevLogger {

    // Toggleable logging flags for different modules
    // public static boolean menuLogging = true;
    public static boolean pbToVileLogging = true;
    public static boolean vileToPbLogging = true;
    // public static boolean playerLookupLogging = true;
    public static boolean itemCountLogging = false;
    public static boolean pureNetworthLogging = true;
    public static boolean apiAccessorLogging = true;

    // Method to log messages for Pb to Vile conversion
    public static void logPbToVile(String message, Object... args) {
        if (pbToVileLogging) {
            System.out.printf(message, args);
        }
    }

    // Method to log messages for Vile to Pb conversion
    public static void logVileToPb(String message, Object... args) {
        if (vileToPbLogging) {
            System.out.printf(message, args);
        }
    }

    // Method to log messages for item counting in PitPandaPureCounter
    public static void logItemCount(String message, Object... args) {
        if (itemCountLogging) {
            System.out.printf(message, args);
        }
    }

    // Method to log messages for calculating total networth in PitPandaPureCounter
    public static void logTotalNetworth(String message, Object... args) {
        if (pureNetworthLogging) {
            System.out.printf(message, args);
        }
    }

    public static void loggingApiAccessor(String message, Object... args) {
        if (apiAccessorLogging) {
            System.out.printf(message, args);
        }
    }

    // Add similar log methods for other modules as needed
}
