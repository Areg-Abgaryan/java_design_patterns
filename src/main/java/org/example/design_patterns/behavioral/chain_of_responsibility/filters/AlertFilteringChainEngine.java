/**
 * Copyright (c) 2024 Areg Abgaryan
 */

package org.example.design_patterns.behavioral.chain_of_responsibility.filters;

import org.example.design_patterns.behavioral.chain_of_responsibility.alert.Alert;

import java.util.Set;

/**
 * Singleton class that manages the chain of filters for processing alerts.
 * The filters are applied in a predefined order to filter out alerts based on specific criteria.
 */
public class AlertFilteringChainEngine {

    private final FilterAlertBase alertFilterChain;

    private static class AlertEngineInstanceHolder {
        //  Create Alert Filtering Engine
        private static final AlertFilteringChainEngine instance = new AlertFilteringChainEngine();
    }

    public static AlertFilteringChainEngine getInstance() {
        return AlertEngineInstanceHolder.instance;
    }

    private AlertFilteringChainEngine() {
        //  Initialize filters with predefined order for alert notifications
        final var categoryFilter = new FilterByCategory(null);
        final var criticalityFilter = new FilterByCriticality(categoryFilter);
        this.alertFilterChain = new FilterByStatus(criticalityFilter);
    }

    public Set<Alert> process(Set<Alert> alerts) {
        alertFilterChain.doFilter(alerts);
        return alerts;
    }
}
