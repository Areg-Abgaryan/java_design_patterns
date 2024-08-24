/**
 * Copyright (c) 2024 Areg Abgaryan
 */

package org.example.demo;

import org.example.design_patterns.structural.facade.CoffeeMachineFacade;
import org.example.design_patterns.structural.facade.CoffeeStrength;
import org.example.design_patterns.structural.facade.CoffeeType;
import org.example.design_patterns.structural.facade.SugarStrength;

public class DemoStructuralDPs {

    public static void demo() {
        demoFacade();
    }

    private static void demoFacade() {

        //  Facade
        final CoffeeMachineFacade coffeeMachine = CoffeeMachineFacade.getInstance();
        coffeeMachine.makeCoffee(CoffeeType.ESPRESSO, CoffeeStrength.MEDIUM, SugarStrength.NO_SUGAR);
    }
}
