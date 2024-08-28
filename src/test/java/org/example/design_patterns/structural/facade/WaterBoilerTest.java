/**
 * Copyright (c) 2024 Areg Abgaryan
 */

package org.example.design_patterns.structural.facade;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

class WaterBoilerTest {

    private static WaterBoiler waterBoiler;

    @BeforeAll
    static void setUp() {
        waterBoiler = WaterBoiler.getInstance();
    }

    @Test
    @Order(1)
    void testSingletonInstance() {
        //  Ensure that WaterBoiler is a singleton
        final WaterBoiler anotherInstance = WaterBoiler.getInstance();
        Assertions.assertSame(waterBoiler, anotherInstance, "WaterBoiler should be a singleton");
    }

    @Test
    @Order(2)
    void testBoilWaterWhileAlreadyBoilingThrowsException() {
        //  Boil water (this will set isBoilerInUse to true)
        new Thread(() -> waterBoiler.boilWater()).start();

        //  Wait briefly to ensure the boiler is in use
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        //  Attempt to boil water again while the boiler is in use
        final Exception exception = Assertions.assertThrows(CoffeeMachineException.class, () -> waterBoiler.boilWater());

        //  Verify that the correct exception message is thrown
        Assertions.assertEquals("Error: Boiler is already in use. Please wait", exception.getMessage());
    }
}