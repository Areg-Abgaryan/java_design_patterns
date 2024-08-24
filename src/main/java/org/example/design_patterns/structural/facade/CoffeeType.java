/**
 * Copyright (c) 2024 Areg Abgaryan
 */

package org.example.design_patterns.structural.facade;

public enum CoffeeType {
    ESPRESSO(12),         // 10 grams of coffee beans required
    CAPPUCCINO(18),       // 15 grams of coffee beans required
    LATTE(14),            // 12 grams of coffee beans required
    AMERICANO(10),        // 8 grams of coffee beans required
    MOCHA(16);            // 14 grams of coffee beans required

    private final int beansQuantityInGrams;

    CoffeeType(int beansQuantityInGrams) {
        this.beansQuantityInGrams = beansQuantityInGrams;
    }

    public int getBeansQuantityInGrams() {
        return beansQuantityInGrams;
    }
}