/**
 * Copyright (c) 2024 Areg Abgaryan
 */

package org.example.design_patterns.behavioral.chain_of_responsibility.filters;

import org.example.design_patterns.behavioral.chain_of_responsibility.alert.Alert;
import org.example.design_patterns.behavioral.chain_of_responsibility.alert.AlertFilteringException;
import org.example.design_patterns.behavioral.chain_of_responsibility.alert.AlertStatus;

import java.util.Set;

public class FilterByStatus extends FilterAlertBase {

    public FilterByStatus(FilterAlertBase nextFilter) {
        super(nextFilter);
    }

    @Override
    void filter(Set<Alert> alerts) {
        //  Remove alerts that do not meet the status condition
        alerts.removeIf(alert -> {
            final AlertStatus status = alert.getStatus();

            if (status == null) {
                throw new AlertFilteringException("Error: Alert status cannot be null for alert: " + alert.getName());
            }

            //  Remove if the alert status is neither NEW nor IN_PROGRESS
            return !status.equals(AlertStatus.NEW) && !status.equals(AlertStatus.IN_PROGRESS);
        });
    }
}