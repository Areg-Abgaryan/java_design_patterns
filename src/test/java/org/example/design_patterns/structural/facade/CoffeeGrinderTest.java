/**
 * Copyright (c) 2024 Areg Abgaryan
 */

package org.example.design_patterns.structural.facade;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

class CoffeeGrinderTest {

    private static CoffeeGrinder coffeeGrinder;

    @BeforeAll
    static void setup() {
        coffeeGrinder = CoffeeGrinder.getInstance();
    }

    @Test
    @Order(1)
    void testGrindBeansWithValidCoffeeType() {
        final int initialQuantity = coffeeGrinder.getCoffeeQuantity();
        coffeeGrinder.grindBeans(CoffeeType.MOCHA);

        final int finalQuantity = coffeeGrinder.getCoffeeQuantity();
        Assertions.assertEquals(initialQuantity - CoffeeType.MOCHA.getBeansQuantityInGrams(), finalQuantity);
    }

    @Test
    @Order(2)
    void testGrindBeansWithNullCoffeeType() {
        Assertions.assertThrows(CoffeeMachineException.class, () -> coffeeGrinder.grindBeans(null));
    }

    /*@Test
    @Order(3)
    void testGrindBeansWhenGrinderIsInUse() {
        //  Start grinding
        final var grindingThread = new Thread(() -> coffeeGrinder.grindBeans(CoffeeType.CAPPUCCINO));
        grindingThread.start();

        //  Give the grinding process a moment to start
        try {
            //  Wait a bit to ensure grinding has started
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        //  Try to grind another batch while the first is still in progress
        Assertions.assertThrows(CoffeeMachineException.class, () -> coffeeGrinder.grindBeans(CoffeeType.ESPRESSO),
                "Error: Grinder is already in use. Please wait");

        //  Wait for the grinding thread to finish
        try {
            grindingThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }*/

    @Test
    @Order(4)
    void testGrindBeansWithNegativeBeans() {
        //  Try grinding beans, expecting a failure due to insufficient beans
        Assertions.assertThrows(CoffeeMachineException.class, () -> coffeeGrinder.refillBeans(-290));
    }

    @Test
    @Order(5)
    void testRefillBeansWithValidAmount() {
        coffeeGrinder.grindBeans(CoffeeType.CAPPUCCINO);
        Assertions.assertDoesNotThrow(() -> coffeeGrinder.refillBeans(CoffeeType.CAPPUCCINO.getBeansQuantityInGrams()));
    }

    @Test
    @Order(6)
    void testRefillBeansWithExcessiveAmount() {
        Assertions.assertThrows(CoffeeMachineException.class, () -> coffeeGrinder.refillBeans(100));
    }

    @Test
    @Order(7)
    void testRefillBeansWithNegativeAmount() {
        Assertions.assertThrows(CoffeeMachineException.class, () -> coffeeGrinder.refillBeans(-10));
    }

    @Test
    @Order(8)
    void testGrindBeansReducesCoffeeQuantity() {
        final int initialQuantity = coffeeGrinder.getCoffeeQuantity();
        coffeeGrinder.grindBeans(CoffeeType.AMERICANO);

        final int finalQuantity = coffeeGrinder.getCoffeeQuantity();
        Assertions.assertEquals(initialQuantity - CoffeeType.AMERICANO.getBeansQuantityInGrams(), finalQuantity);
    }

    @Test
    @Order(10)
    void testGrindBeansWithInsufficientBeans2() {

        //  Grind coffee beans until the quantity is invalid for this operation
        while (coffeeGrinder.getCoffeeQuantity() >= CoffeeGrinder.getMinCoffeeBeansQuantity()) {
            coffeeGrinder.grindBeans(CoffeeType.CAPPUCCINO);
        }

        //  Attempt to grind with insufficient beans
        final CoffeeMachineException exception = Assertions.assertThrows(CoffeeMachineException.class,
                () -> coffeeGrinder.grindBeans(CoffeeType.AMERICANO));

        //  Verify the exception message
        Assertions.assertEquals("Error: Not enough coffee beans to grind", exception.getMessage());
    }
}