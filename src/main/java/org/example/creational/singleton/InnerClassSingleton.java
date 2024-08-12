/**
 * Copyright (c) 2024 Areg Abgaryan
 */

package org.example.creational.singleton;

public class InnerClassSingleton {

    private InnerClassSingleton() {}

    //  Static inner class with lazy initialization
    //  Responsible for holding the Singleton instance
    //  Thread-safe due to JVM class loading mechanism
    private static class InstanceHolder {
        private static final InnerClassSingleton INSTANCE = new InnerClassSingleton();
    }

    public static InnerClassSingleton getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Singleton instance cannot be cloned.");
    }
}
