/**
 * Copyright (c) 2024 Areg Abgaryan
 */

package org.example.design_patterns.structural.facade;

public class CoffeeMachineFacade {

    private final CoffeeGrinder grinder;
    private final WaterBoiler boiler;
    private final CoffeeMaker maker;

    private CoffeeMachineFacade() {
        this.grinder = CoffeeGrinder.getInstance();
        this.boiler = WaterBoiler.getInstance();
        this.maker = CoffeeMaker.getInstance();
    }

    private static class InstanceHolder {
        private static final CoffeeMachineFacade instance = new CoffeeMachineFacade();
    }

    public static CoffeeMachineFacade getInstance() {
        return CoffeeMachineFacade.InstanceHolder.instance;
    }


    //  Facade's public interface for the client
    public void makeCoffee(CoffeeType type, CoffeeStrength strength, SugarStrength sugar) {

        //  Grind the beans based on coffee type
        grinder.grindBeans(type);

        //  Boil water
        boiler.boilWater();

        //  Make coffee
        maker.brewCoffee(type, strength, sugar);
    }
}
