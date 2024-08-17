/**
 * Copyright (c) 2024 Areg Abgaryan
 */

package org.example.design_patterns.behavioral.chain_of_responsibility.alert;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Alert {
    private String name;
    private String description;
    private AlertStatus status;
    private AlertCriticality criticality;
    private AlertCategory category;
}
