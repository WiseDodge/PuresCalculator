package com.wisedodge.purescalculator.converters;

import com.wisedodge.purescalculator.logging.DevLogger;

public class PbToVileConverter {

    public String convert(double quantity) {
        int wholePart = (int) quantity;
        double fractionalPart = quantity - wholePart;

        double totalVile = wholePart * 18.0 + fractionalPart * 18.0;

        int stacks = (int) (totalVile / 64);
        long remainingVile = Math.round(totalVile % 64);

        totalVile = Math.round(totalVile);

        DevLogger.logPbToVile("Converted %.1f PB to %.0f Vile.%n", quantity, totalVile);

        StringBuilder result = new StringBuilder(String.format("%.1f PB is equivalent to %.0f Vile.", quantity, totalVile));

        if (quantity >= 1.0) {
            appendStackInfo(result, stacks, remainingVile);
        }

        return result.toString();
    }

    protected void appendStackInfo(StringBuilder result, int stacks, long remainingVile) {
        if (stacks > 0) {
            result.append(String.format("%n%d Stacks", stacks));
            if (remainingVile > 0) {
                result.append(String.format(" and %d Vile.%n", remainingVile));
            } else {
                result.append(".%n");
            }
        } else {
            if (remainingVile > 0) {
                result.append(String.format("%n0 Stacks and %d Vile.%n", remainingVile));
            } else {
                result.append("%n0 Stacks and 0 Vile.%n");
            }
        }
    }
}
