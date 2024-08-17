/**
 * Copyright (c) 2024 Areg Abgaryan
 */

package org.example.design_patterns.behavioral.chain_of_responsibility.filters;

import org.example.design_patterns.behavioral.chain_of_responsibility.alert.Alert;
import org.example.design_patterns.behavioral.chain_of_responsibility.alert.AlertCriticality;
import org.example.design_patterns.behavioral.chain_of_responsibility.alert.AlertFilteringException;

import java.util.Set;

public class FilterByCriticality extends FilterAlertBase {

    public FilterByCriticality(FilterAlertBase nextFilter) {
        super(nextFilter);
    }

    @Override
    void filter(Set<Alert> alerts) {
        //  Remove alerts that do not meet the criticality condition
        alerts.removeIf(alert -> {
            final AlertCriticality criticality = alert.getCriticality();

            if (criticality == null) {
                throw new AlertFilteringException("Error: Alert criticality cannot be null for alert: " + alert.getName());
            }

            //  Remove if the alert criticality is not WARNING, IMMEDIATE or CRITICAL
            return !criticality.equals(AlertCriticality.WARNING) &&
                    !criticality.equals(AlertCriticality.IMMEDIATE) &&
                    !criticality.equals(AlertCriticality.CRITICAL);
        });
    }
}
