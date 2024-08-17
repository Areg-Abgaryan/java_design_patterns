/**
 * Copyright (c) 2024 Areg Abgaryan
 */

package org.example.design_patterns.behavioral.chain_of_responsibility.filters;

import org.example.design_patterns.behavioral.chain_of_responsibility.alert.Alert;
import org.example.design_patterns.behavioral.chain_of_responsibility.alert.AlertCategory;
import org.example.design_patterns.behavioral.chain_of_responsibility.alert.AlertFilteringException;

import java.util.Set;

public class FilterByCategory extends FilterAlertBase {

    public FilterByCategory(FilterAlertBase nextFilter) {
        super(nextFilter);
    }

    @Override
    void filter(Set<Alert> alerts) {
        //  Remove alerts that do not meet the category condition
        alerts.removeIf(alert -> {
            final AlertCategory category = alert.getCategory();
            if (category == null) {
                throw new AlertFilteringException("Error: Alert category cannot be null for alert: " + alert.getName());
            }

            //  Remove if the alert category is not UNKNOWN
            return category.equals(AlertCategory.UNKNOWN);
        });
    }
}
