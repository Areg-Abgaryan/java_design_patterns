/**
 * Copyright (c) 2024 Areg Abgaryan
 */

package org.example.design_patterns.behavioral.memento;

import java.util.Stack;

/**
 * Caretaker: Manages the saved states (Snapshots). It stores and retrieves Snapshots as needed.
 */
public class TextCaretaker {

    private final TextEditor textEditor;
    private final Stack<TextEditor.Snapshot> undoSnapshotStack;
    private final Stack<TextEditor.Snapshot> redoSnapshotStack;

    public TextCaretaker() {
        this.textEditor = new TextEditor();
        this.undoSnapshotStack = new Stack<>();
        this.redoSnapshotStack = new Stack<>();
    }

    public void write(String text) {
        textEditor.setText(text);
        undoSnapshotStack.push(textEditor.takeSnapshot());
        //  Clear the redo stack when a new action is performed
        redoSnapshotStack.clear();
    }

    public String getText() {
        return stackTopOrEmpty(undoSnapshotStack);
    }

    public String undo() {
        if (undoSnapshotStack.isEmpty()) {
            return "";
        }

        final TextEditor.Snapshot snapshot = undoSnapshotStack.pop();
        redoSnapshotStack.push(snapshot);

        return stackTopOrEmpty(undoSnapshotStack);
    }

    public String redo() {
        if (redoSnapshotStack.isEmpty()) {
            return stackTopOrEmpty(undoSnapshotStack);
        }

        final TextEditor.Snapshot snapshot = redoSnapshotStack.pop();
        //  Push the snapshot back to the undo stack
        undoSnapshotStack.push(snapshot);
        return textEditor.restore(snapshot);
    }

    private static String stackTopOrEmpty(Stack<TextEditor.Snapshot> st) {
        return st.isEmpty() ? "" : st.peek().text();
    }
}