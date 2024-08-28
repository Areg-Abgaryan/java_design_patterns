/**
 * Copyright (c) 2024 Areg Abgaryan
 */

package org.example.design_patterns.behavioral.memento;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

class TextCaretakerTest {

    private static TextCaretaker textCaretaker;

    @BeforeEach
    void setUp() {
        textCaretaker = new TextCaretaker();
    }

    @Test
    @Order(1)
    void testWrite() {
        textCaretaker.write("Hello, world!");
        Assertions.assertEquals("Hello, world!", textCaretaker.getText());
    }

    @Test
    @Order(2)
    void testUndo() {
        textCaretaker.write("First text");
        textCaretaker.write("Second text");

        Assertions.assertEquals("Second text", textCaretaker.getText());
        textCaretaker.undo();
        Assertions.assertEquals("First text", textCaretaker.getText());
    }

    @Test
    @Order(3)
    void testRedo() {
        textCaretaker.write("First text");
        textCaretaker.write("Second text");
        textCaretaker.undo();

        Assertions.assertEquals("First text", textCaretaker.getText());
        textCaretaker.redo();
        Assertions.assertEquals("Second text", textCaretaker.getText());
    }

    @Test
    @Order(4)
    void testMultipleUndoRedo() {
        textCaretaker.write("First text");
        textCaretaker.write("Second text");
        textCaretaker.write("Third text");

        textCaretaker.undo();
        textCaretaker.undo();
        textCaretaker.redo();
        textCaretaker.redo();

        Assertions.assertEquals("Third text", textCaretaker.getText());
    }

    @Test
    @Order(5)
    void testUndoWhenEmpty() {
        textCaretaker.undo();
        Assertions.assertEquals("", textCaretaker.getText());
    }

    @Test
    @Order(6)
    void testRedoWhenEmpty() {
        textCaretaker.redo();
        Assertions.assertEquals("", textCaretaker.getText());
    }
}
