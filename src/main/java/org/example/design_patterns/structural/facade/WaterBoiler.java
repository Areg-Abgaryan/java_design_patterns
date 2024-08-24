/**
 * Copyright (c) 2024 Areg Abgaryan
 */

package org.example.design_patterns.structural.facade;

class WaterBoiler implements ICoffeeProcessDetail {

    private static boolean isBoilerInUse;
    private boolean isWaterBoiled = false;
    private int waterLevel;                                 //  In milliliters
    private static final int MIN_WATER_LEVEL = 100;         //  In milliliters
    private static final int MAX_WATER_LEVEL = 2000;        //  In milliliters
    private static final long BOILING_PROCESS_MILLIS = 5000;

    private WaterBoiler() {
        refillWater(MAX_WATER_LEVEL);
    }

    private static class WaterBoilerInstanceHolder {
        //  Create Water Boiler
        private static final WaterBoiler instance = new WaterBoiler();
    }

    static WaterBoiler getInstance() {
        return WaterBoiler.WaterBoilerInstanceHolder.instance;
    }

    void boilWater() {
        if (isBoilerInUse) {
            throw new CoffeeMachineException("Error: Boiler is already in use. Please wait");
        }

        if (waterLevel < MIN_WATER_LEVEL) {
            throw new CoffeeMachineException("Error: Not enough water to boil");
        }

        //  Water is already boiled
        if (isWaterBoiled) {
            return;
        }

        boil();
    }

    //  Simulate boiling process
    private void boil() {
        isBoilerInUse = true;
        holdForMilliseconds(BOILING_PROCESS_MILLIS, CoffeeProcessOperation.Boiling);
        isBoilerInUse = false;
        isWaterBoiled = true;
    }

    private void refillWater(int amount) {
        if (amount <= 0) {
            throw new CoffeeMachineException("Error: Quantity to refill must be positive");
        } else if (amount + waterLevel > MAX_WATER_LEVEL) {
            throw new CoffeeMachineException("Error: Quantity to refill is too much");
        }

        waterLevel += amount;

        //  Reset boiling status when water is refilled
        isWaterBoiled = false;
        isBoilerInUse = false;
    }
}
