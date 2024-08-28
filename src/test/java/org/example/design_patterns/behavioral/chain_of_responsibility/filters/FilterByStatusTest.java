/**
 * Copyright (c) 2024 Areg Abgaryan
 */

package org.example.design_patterns.behavioral.chain_of_responsibility.filters;

import org.example.design_patterns.behavioral.chain_of_responsibility.alert.Alert;
import org.example.design_patterns.behavioral.chain_of_responsibility.alert.AlertCategory;
import org.example.design_patterns.behavioral.chain_of_responsibility.alert.AlertCriticality;
import org.example.design_patterns.behavioral.chain_of_responsibility.alert.AlertFilteringException;
import org.example.design_patterns.behavioral.chain_of_responsibility.alert.AlertStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class FilterByStatusTest {

    /**
     * Test FilterByStatus with nullable alerts' set.
     */
    @Test
    @Order(1)
    void testStatusFilterWithNullAlerts() {
        final var filter = new FilterByStatus(null);
        Assertions.assertThrows(AlertFilteringException.class, () -> filter.doFilter(null));
    }

    /**
     * Test FilterByStatus with empty alerts' set.
     */
    @Test
    @Order(2)
    void testStatusFilterWithEmptyAlerts() {
        final Set<Alert> alerts = new HashSet<>();
        final var filter = new FilterByStatus(null);
        Assertions.assertThrows(AlertFilteringException.class, () -> filter.doFilter(alerts));
    }

    /**
     * Test FilterByStatus with nullable status.
     */
    @Test
    @Order(3)
    void testStatusFilterWithNullAlertStatus() {
        final String name = "Alert 1";
        final Alert alert = new Alert(name, "Test alert", null, AlertCriticality.CRITICAL, AlertCategory.APPLICATION);

        final var filter = new FilterByStatus(null);

        final AlertFilteringException exception = Assertions.assertThrows(AlertFilteringException.class, () -> filter.doFilter(new HashSet<>(Set.of(alert))));

        //  Check the exception message
        Assertions.assertEquals("Error: Alert status cannot be null for alert: " + name, exception.getMessage());
    }

    /**
     * Test FilterByStatus with valid data.
     */
    @Test
    @Order(4)
    void testStatusFilter() {
        //  Set up alerts
        final Set<Alert> alerts = new HashSet<>();
        alerts.add(new Alert("Alert 1", "Critical alert", AlertStatus.NEW, AlertCriticality.CRITICAL, AlertCategory.SECURITY));
        alerts.add(new Alert("Alert 2", "Info alert", AlertStatus.IN_PROGRESS, AlertCriticality.INFO, AlertCategory.UNKNOWN));
        alerts.add(new Alert("Alert 3", "Auto alert", AlertStatus.RESOLVED, AlertCriticality.AUTO, AlertCategory.DATABASE));
        alerts.add(new Alert("Alert 4", "None alert", AlertStatus.FAILED, AlertCriticality.NONE, AlertCategory.NETWORK));
        alerts.add(new Alert("Alert 5", "Warning alert", AlertStatus.NEW, AlertCriticality.WARNING, AlertCategory.APPLICATION));
        alerts.add(new Alert("Alert 6", "Immediate alert", AlertStatus.IN_PROGRESS, AlertCriticality.IMMEDIATE, AlertCategory.PERFORMANCE));

        //  Apply filter
        final var filter = new FilterByStatus(null);
        filter.doFilter(alerts);

        //  Verify that all alerts with invalid status values are removed
        Assertions.assertTrue(alerts.stream().allMatch(AlertFilterTestUtils.getFilteringByStatusCondition()));
    }
}
