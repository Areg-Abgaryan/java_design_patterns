/**
 * Copyright (c) 2024 Areg Abgaryan
 */

package org.example.design_patterns.structural.facade;

import lombok.Getter;

import java.util.Arrays;

@Getter
class CoffeeGrinder implements ICoffeeProcessDetail {

    private static boolean isGrinderInUse;
    private int coffeeQuantity;
    private static final int MIN_COFFEE_BEANS_QUANTITY = calculateCoffeeBeansMaxQuantity();
    private static final int MAX_COFFEE_BEANS_QUANTITY = 300;       //  In grams
    private static final long GRINDING_PROCESS_MILLIS = 1000;       //  Small value for easier testing

    private CoffeeGrinder() {
        refillBeans(MAX_COFFEE_BEANS_QUANTITY);
    }

    static int getMinCoffeeBeansQuantity() {
        return MIN_COFFEE_BEANS_QUANTITY;
    }

    private static class CoffeeGrinderInstanceHolder {
        //  Create Coffee Grinder
        private static final CoffeeGrinder instance = new CoffeeGrinder();
    }

    static CoffeeGrinder getInstance() {
        return CoffeeGrinder.CoffeeGrinderInstanceHolder.instance;
    }

    void grindBeans(CoffeeType coffeeType) {
        if (coffeeType == null) {
            throw new CoffeeMachineException("Error: Coffee type cannot be null");
        }

        if (isGrinderInUse) {
            throw new CoffeeMachineException("Error: Grinder is already in use. Please wait");
        }

        if (coffeeQuantity < MIN_COFFEE_BEANS_QUANTITY) {
            throw new CoffeeMachineException("Error: Not enough coffee beans to grind");
        }

        grind(coffeeType);
    }

    void refillBeans(int amount) {
        if (amount <= 0) {
            throw new CoffeeMachineException("Error: Quantity to refill must be positive");
        } else if (amount + coffeeQuantity > MAX_COFFEE_BEANS_QUANTITY) {
            throw new CoffeeMachineException("Error: Quantity to refill is too much");
        }

        coffeeQuantity += amount;

        //  Reset grinding status when water is refilled
        isGrinderInUse = false;
    }

    //  Simulate grinding process
    private void grind(CoffeeType type) {
        isGrinderInUse = true;
        holdForMilliseconds(GRINDING_PROCESS_MILLIS, CoffeeProcessOperation.Grinding);

        //  Deduct the beans used for this operation
        coffeeQuantity -= type.getBeansQuantityInGrams();
        isGrinderInUse = false;
    }

    private static int calculateCoffeeBeansMaxQuantity() {
        return Arrays.stream(CoffeeType.values())
                .mapToInt(CoffeeType::getBeansQuantityInGrams)
                .filter(type -> type >= 0)
                .min().orElse(0);
    }
}
