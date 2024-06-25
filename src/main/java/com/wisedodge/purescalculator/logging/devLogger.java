package com.wisedodge.purescalculator.logging;

public class devLogger {

  // * Toggleable logging flags for different modules
  // ? public static boolean menuLogging = true;
  // ? public static boolean playerLookupLogging = true;
  public static final boolean pbToVileLogging = true;
  public static final boolean vileToPbLogging = true;
  public static final boolean itemCountLogging = false;
  public static final boolean pureNetworthLogging = true;
  public static final boolean kosListLogging = true;

  private devLogger() {
    // Private constructor to prevent external instantiation
  }

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

  // Method to log messages for item counting in pitPandaPureCounter
  public static void logItemCount(String message, Object... args) {
    if (itemCountLogging) {
      System.out.printf(message, args);
    }
  }

  // Method to log messages for calculating total networth in pitPandaPureCounter
  public static void logTotalNetworth(String message, Object... args) {
    if (pureNetworthLogging) {
      System.out.printf(message, args);
    }
  }

  public static void loggingKosList(String message, Object... args) {
    if (kosListLogging) {
      System.out.printf(message, args);
    }
  }
}
