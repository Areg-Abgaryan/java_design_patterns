/**
 * Copyright (c) 2024 Areg Abgaryan
 */

package org.example.design_patterns.structural.facade;

public class CoffeeMachineException extends RuntimeException {
    CoffeeMachineException(String message) {
        super(message);
    }
}
