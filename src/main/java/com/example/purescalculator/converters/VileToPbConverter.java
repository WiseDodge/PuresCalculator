package com.example.purescalculator.converters;

public class VileToPbConverter {

    public double convert(double quantity) {
        // Assuming 1 PB = 10 pants, and 1 pants = 2 Vile
        return quantity / 2.0 / 10.0;
    }
}
// work in progress.