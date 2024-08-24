/**
 * Copyright (c) 2024 Areg Abgaryan
 */

package org.example.design_patterns.structural.facade;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

final class CoffeeMachineFacadeTest {

    private static CoffeeMachineFacade coffeeMachineFacade;

    @BeforeAll
    static void setup() {
        coffeeMachineFacade = CoffeeMachineFacade.getInstance();
    }

    @Test
    @Order(1)
    void testSingletonInstance() {
        //  Ensure that CoffeeMachineFacade is a singleton
        final CoffeeMachineFacade anotherInstance = CoffeeMachineFacade.getInstance();
        Assertions.assertSame(coffeeMachineFacade, anotherInstance, "CoffeeMachineFacade should be a singleton");
    }
}
