/**
 * Copyright (c) 2024 Areg Abgaryan
 */

package org.example.design_patterns.behavioral.memento;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextEditorTest {

    private TextEditor textEditor;

    @BeforeEach
    void setUp() {
        textEditor = new TextEditor();
    }

    @Test
    @Order(1)
    void testSetAndGetText() {
        textEditor.setText("Hello, world!");
        assertEquals("Hello, world!", textEditor.getText());
    }

    @Test
    @Order(2)
    void testTakeSnapshot() {
        textEditor.setText("First text");
        final TextEditor.Snapshot snapshot = textEditor.takeSnapshot();
        assertEquals("First text", snapshot.text());

        textEditor.setText("Second text");
        assertEquals("Second text", textEditor.getText());
        assertEquals("First text", snapshot.text());
    }

    @Test
    @Order(3)
    void testRestoreSnapshot() {
        textEditor.setText("First text");
        final TextEditor.Snapshot snapshot = textEditor.takeSnapshot();
        textEditor.setText("Second text");

        textEditor.restore(snapshot);
        assertEquals("First text", textEditor.getText());
    }
}
