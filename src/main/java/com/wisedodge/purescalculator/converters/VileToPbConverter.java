package com.wisedodge.purescalculator.converters;
import com.wisedodge.purescalculator.logging.DevLogger;


public class VileToPbConverter {

    public String convert(double quantity) {
        int stacks = (int) (quantity / 64);
        int remainingVile = (int) (quantity % 64);

        double totalPb = quantity / 18.0;

        DevLogger.logVileToPb("Converted %.0f Vile to %.1f PB.%n", quantity, totalPb);

        StringBuilder result = new StringBuilder(String.format("%.0f Vile is equivalent to %.1f PB.", quantity, totalPb));

        if (quantity >= 1.0) {
            appendStackInfo(result, stacks, remainingVile);
        }

        return result.toString();
    }

    protected void appendStackInfo(StringBuilder result, int stacks, int remainingVile) {
        if (stacks > 0) {
            result.append(String.format("%n%d Stacks", stacks));
            if (remainingVile > 0) {
                result.append(String.format(" and %d Vile.", remainingVile));
            } else {
                result.append(".");
            }
        } else {
            if (remainingVile > 0) {
                result.append(String.format("%n%d Vile.", remainingVile));
            } else {
                result.append("%n0 Vile.");
            }
        }
    }
}
