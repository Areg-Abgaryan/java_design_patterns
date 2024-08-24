/**
 * Copyright (c) 2024 Areg Abgaryan
 */

package org.example.design_patterns.structural.facade;

class CoffeeMaker implements ICoffeeProcessDetail {

    private boolean isBrewing;

    private CoffeeMaker() {}

    private static class CoffeeMakerInstanceHolder {
        //  Create Water Boiler
        private static final CoffeeMaker instance = new CoffeeMaker();
    }

    static CoffeeMaker getInstance() {
        return CoffeeMaker.CoffeeMakerInstanceHolder.instance;
    }

    void brewCoffee(CoffeeType coffeeType, CoffeeStrength coffeeStrength, SugarStrength sugarStrength) {
        if (coffeeType == null || coffeeStrength == null || sugarStrength == null) {
            throw new CoffeeMachineException("Error: Coffee type, strength or sugar strength cannot be null");
        }

        if (isBrewing) {
            throw new CoffeeMachineException("Error: Coffee maker is already in use. Please wait");
        }

        //  Simulate brewing process
        isBrewing = true;

        //  Simulate brewing time based on coffee strength
        final int brewingTime = getBrewingTimeBasedOnStrength(coffeeStrength);
        holdForMilliseconds(brewingTime, CoffeeProcessOperation.Brewing);
        isBrewing = false;
    }

    private int getBrewingTimeBasedOnStrength(CoffeeStrength strength) {
        return switch (strength) {
            case WEAK -> 2000;
            case MEDIUM -> 2500;
            case STRONG -> 3000;
            case EXTRA_STRONG -> 3500;
        };
    }
}
