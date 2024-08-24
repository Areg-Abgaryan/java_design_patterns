/**
 * Copyright (c) 2024 Areg Abgaryan
 */

package org.example.design_patterns.behavioral.chain_of_responsibility.filters;

import org.example.design_patterns.behavioral.chain_of_responsibility.alert.Alert;
import org.example.design_patterns.behavioral.chain_of_responsibility.alert.AlertCategory;
import org.example.design_patterns.behavioral.chain_of_responsibility.alert.AlertCriticality;
import org.example.design_patterns.behavioral.chain_of_responsibility.alert.AlertStatus;

import java.util.function.Predicate;

final class AlertFilterTestUtils {
    static Predicate<Alert> getAllFilteringConditions() {
        return getFilteringByStatusCondition()
                .and(getFilteringByCriticalityCondition())
                .and(getFilteringByCategoryCondition());
    }

    static Predicate<Alert> getFilteringByStatusCondition() {
        return alert -> alert.getStatus() != AlertStatus.RESOLVED && alert.getStatus() != AlertStatus.FAILED;
    }

    static Predicate<Alert> getFilteringByCriticalityCondition() {
        return alert -> alert.getCriticality() != AlertCriticality.AUTO &&
                        alert.getCriticality() != AlertCriticality.NONE &&
                        alert.getCriticality() != AlertCriticality.INFO;
    }

    static Predicate<Alert> getFilteringByCategoryCondition() {
        return alert -> alert.getCategory() != AlertCategory.UNKNOWN;
    }
}
