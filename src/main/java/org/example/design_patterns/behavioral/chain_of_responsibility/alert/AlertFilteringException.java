/**
 * Copyright (c) 2024 Areg Abgaryan
 */

package org.example.design_patterns.behavioral.chain_of_responsibility.alert;

public class AlertFilteringException extends RuntimeException {

    public AlertFilteringException(String message) {
        super(message);
    }
}
