/**
 * Copyright (c) 2024 Areg Abgaryan
 */

package org.example.design_patterns.behavioral.chain_of_responsibility.filters;

import org.example.design_patterns.behavioral.chain_of_responsibility.alert.Alert;
import org.example.design_patterns.behavioral.chain_of_responsibility.alert.AlertFilteringException;

import java.util.Set;

abstract public class FilterAlertBase {

    protected FilterAlertBase nextFilter;

    protected FilterAlertBase(FilterAlertBase nextFilter) {
        this.nextFilter = nextFilter;
    }

    //  This method manages the filter chain
    public void doFilter(Set<Alert> alerts) {
        if (alerts == null || alerts.isEmpty()) {
            throw new AlertFilteringException("Error: Alerts cannot be null or empty");
        }

        //  Apply the filter's specific logic
        filter(alerts);

        //  Pass the filtered alerts to the next filter, if any
        if (nextFilter != null) {
            nextFilter.doFilter(alerts);
        }
    }

    abstract void filter(Set<Alert> alert);
}
