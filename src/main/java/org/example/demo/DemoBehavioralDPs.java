/**
 * Copyright (c) 2024 Areg Abgaryan
 */

package org.example.demo;

import org.example.design_patterns.behavioral.chain_of_responsibility.alert.Alert;
import org.example.design_patterns.behavioral.chain_of_responsibility.alert.AlertCategory;
import org.example.design_patterns.behavioral.chain_of_responsibility.alert.AlertCriticality;
import org.example.design_patterns.behavioral.chain_of_responsibility.alert.AlertStatus;
import org.example.design_patterns.behavioral.chain_of_responsibility.filters.AlertFilteringChainEngine;
import org.example.design_patterns.behavioral.memento.TextCaretaker;

import java.util.HashSet;
import java.util.Set;

public class DemoBehavioralDPs {

    public static void demo() {
        demoChainOfResponsibility();
        demoMemento();
    }


    private static void demoChainOfResponsibility() {

        //  Chain of Responsibility
        final AlertFilteringChainEngine engine = AlertFilteringChainEngine.getInstance();
        final Set<Alert> alerts = new HashSet<>();
        alerts.add(new Alert("Alert 1", "Auto alert", AlertStatus.NEW, AlertCriticality.AUTO, AlertCategory.APPLICATION));
        alerts.add(new Alert("Alert 2", "None alert", AlertStatus.IN_PROGRESS, AlertCriticality.NONE, AlertCategory.DATABASE));
        alerts.add(new Alert("Alert 3", "Info alert", AlertStatus.RESOLVED, AlertCriticality.INFO, AlertCategory.UNKNOWN));
        alerts.add(new Alert("Alert 4", "Warning alert", AlertStatus.FAILED, AlertCriticality.WARNING, AlertCategory.PERFORMANCE));
        alerts.add(new Alert("Alert 5", "Immediate alert", AlertStatus.IN_PROGRESS, AlertCriticality.IMMEDIATE, AlertCategory.NETWORK));
        alerts.add(new Alert("Alert 6", "Critical alert", AlertStatus.NEW, AlertCriticality.CRITICAL, AlertCategory.SECURITY));

        final Set<Alert> filteredAlerts = engine.process(alerts);
        System.out.println(filteredAlerts.size());
    }

    private static void demoMemento() {

        //  Memento
        final var textCaretaker = new TextCaretaker();
        textCaretaker.write("1st text");
        textCaretaker.write("2nd text");

        System.out.println("Original State: " + textCaretaker.getText());

        System.out.println("After Undo: " + textCaretaker.undo());
        System.out.println("After Undo: " + textCaretaker.undo());

        System.out.println("After Redo: " + textCaretaker.redo());
        System.out.println("After Redo: " + textCaretaker.redo());
        System.out.println("After Redo: " + textCaretaker.redo());

        System.out.println("After Undo: " + textCaretaker.undo());
        System.out.println("After Redo: " + textCaretaker.redo());
    }
}
