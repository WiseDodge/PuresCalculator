package com.example.purescalculator.converters;

public class PbToVileConverter {

    public String convert(double quantity) {
        int wholePart = (int) quantity;
        double fractionalPart = quantity - wholePart;

        double totalVile = wholePart * 18.0 + fractionalPart * 18.0;

        int stacks = (int) (totalVile / 64);
        int remainingVile = (int) (totalVile % 64);

        totalVile = Math.round(totalVile);
        remainingVile = Math.round(remainingVile);

        System.out.println(String.format("Converted %.1f PB to %.0f Vile.", quantity, totalVile));

        StringBuilder result = new StringBuilder(String.format("%.1f PB is equivalent to %.0f Vile.", quantity, totalVile));

        if (quantity >= 1.0) {
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

        return result.toString();
    }
}