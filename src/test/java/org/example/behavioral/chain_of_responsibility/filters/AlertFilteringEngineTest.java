/**
 * Copyright (c) 2024 Areg Abgaryan
 */

package org.example.behavioral.chain_of_responsibility.filters;

import org.example.design_patterns.behavioral.chain_of_responsibility.alert.Alert;
import org.example.design_patterns.behavioral.chain_of_responsibility.alert.AlertCategory;
import org.example.design_patterns.behavioral.chain_of_responsibility.alert.AlertCriticality;
import org.example.design_patterns.behavioral.chain_of_responsibility.alert.AlertStatus;
import org.example.design_patterns.behavioral.chain_of_responsibility.filters.AlertFilteringEngine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

final class AlertFilteringEngineTest {

    /**
     * Test to ensure only one instance is created for the AlertFilteringEngine class.
     */
    @Test
    @Order(1)
    void testEngineSingletonBehavior() {
        final AlertFilteringEngine instance1 = AlertFilteringEngine.getInstance();
        final AlertFilteringEngine instance2 = AlertFilteringEngine.getInstance();

        //  Assert that both instances are the same
        Assertions.assertSame(instance1, instance2, "AlertFilteringEngine should be a singleton");
    }

    /**
     * Test AlertFilteringEngine with valid data.
     */
    @Test
    @Order(2)
    void testProcessWithAllFilters() {
        final AlertFilteringEngine engine = AlertFilteringEngine.getInstance();

        //  Create alerts with different statuses, criticalities and categories
        final Set<Alert> alerts = new HashSet<>();
        alerts.add(new Alert("Alert 1", "Desc 1", AlertStatus.NEW, AlertCriticality.CRITICAL, AlertCategory.SECURITY));
        alerts.add(new Alert("Alert 2", "Desc 2", AlertStatus.IN_PROGRESS, AlertCriticality.IMMEDIATE, AlertCategory.UNKNOWN));
        alerts.add(new Alert("Alert 3", "Desc 3", AlertStatus.RESOLVED, AlertCriticality.INFO, AlertCategory.PERFORMANCE));
        alerts.add(new Alert("Alert 4", "Desc 4", AlertStatus.FAILED, AlertCriticality.WARNING, AlertCategory.DATABASE));

        //  Process alerts
        final Set<Alert> filteredAlerts = engine.process(alerts);

        //  Check the results according to the filter chain
        Assertions.assertTrue(filteredAlerts.stream().allMatch(AlertFilterTestUtils.getAllFilteringConditions()));
    }
}
