/**
 * Copyright (c) 2024 Areg Abgaryan
 */

package org.example.design_patterns.structural.facade;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

class CoffeeMakerTest {

    private static CoffeeMaker coffeeMaker;

    @BeforeAll
    static void setUp() {
        coffeeMaker = CoffeeMaker.getInstance();
    }

    @Test
    @Order(1)
    void testSingletonInstance() {
        // Ensure the instance is a singleton
        final CoffeeMaker anotherInstance = CoffeeMaker.getInstance();
        Assertions.assertSame(coffeeMaker, anotherInstance, "CoffeeMaker should be a singleton");
    }

    @Test
    @Order(2)
    void testBrewCoffeeSuccess() {
        //  Test brewing coffee with valid parameters
        Assertions.assertDoesNotThrow(() -> coffeeMaker.brewCoffee(
                CoffeeType.ESPRESSO, CoffeeStrength.MEDIUM, SugarStrength.MEDIUM));
    }

    @Test
    @Order(3)
    void testBrewCoffeeWithNullTypeThrowsException() {
        //  Test that brewing with a null coffee type throws an exception
        final Exception exception = Assertions.assertThrows(CoffeeMachineException.class,
                () -> coffeeMaker.brewCoffee(null, CoffeeStrength.EXTRA_STRONG, SugarStrength.HEAVY));
        Assertions.assertEquals("Error: Coffee type, strength or sugar strength cannot be null", exception.getMessage());
    }

    @Test
    @Order(4)
    void testBrewCoffeeWithNullStrengthThrowsException() {
        //  Test that brewing with a null coffee strength throws an exception
        final Exception exception = Assertions.assertThrows(CoffeeMachineException.class,
                () -> coffeeMaker.brewCoffee(CoffeeType.ESPRESSO, null, SugarStrength.LIGHT));
        Assertions.assertEquals("Error: Coffee type, strength or sugar strength cannot be null", exception.getMessage());
    }

    @Test
    @Order(5)
    void testBrewCoffeeWhileAlreadyBrewingThrowsException() {
        //  Force the isBrewing flag to true to simulate an ongoing brewing process
        try {
            final var isBrewingField = CoffeeMaker.class.getDeclaredField("isBrewing");
            isBrewingField.setAccessible(true);
            isBrewingField.set(coffeeMaker, true);

            // Try to brew another coffee while already brewing
            final Exception exception = Assertions.assertThrows(CoffeeMachineException.class,
                    () -> coffeeMaker.brewCoffee(CoffeeType.LATTE, CoffeeStrength.WEAK, SugarStrength.NO_SUGAR));

            Assertions.assertEquals("Error: Coffee maker is already in use. Please wait", exception.getMessage());

        } catch (NoSuchFieldException | IllegalAccessException e) {
            Assertions.fail("Reflection failed to access isBrewing field.");
        }
    }
}
