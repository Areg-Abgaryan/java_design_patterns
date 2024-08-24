/**
 * Copyright (c) 2024 Areg Abgaryan
 */

package org.example.design_patterns.structural.facade;

interface ICoffeeProcessDetail {

    //  Method to simulate a delay
    default void holdForMilliseconds(long millis, CoffeeProcessOperation operation) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new CoffeeMachineException("Error: " + operation.name() + " process was interrupted");
        }
    }

    enum CoffeeProcessOperation {
        Grinding,
        Boiling,
        Brewing
    }
}
