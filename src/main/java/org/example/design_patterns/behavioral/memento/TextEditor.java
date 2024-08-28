/**
 * Copyright (c) 2024 Areg Abgaryan
 */

package org.example.design_patterns.behavioral.memento;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * TextEditor (Originator): Represents the text editor whose content needs to be saved and restored.
 * It can create a Snapshot (Memento) to save the current content and restore content from a Snapshot.
 */
@Getter(value = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
class TextEditor {

    private String text;

    Snapshot takeSnapshot() {
        return new Snapshot(this.text);
    }

    String restore(Snapshot memento) {
        this.text = memento.text();
        return this.text;
    }

    /**
     * Snapshot (Memento) class
     */
    record Snapshot(String text) { }
}
