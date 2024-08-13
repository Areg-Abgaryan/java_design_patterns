/**
 * Copyright (c) 2024 Areg Abgaryan
 */

package org.example.creational.singleton;

public final class LazyDoubleLockSingleton {

    //  Volatile to ensure that the changes made to this variable are visible to all the threads
    private static volatile LazyDoubleLockSingleton instance = null;

    private LazyDoubleLockSingleton() {}

    public static LazyDoubleLockSingleton getInstance() {
        //  First check (no locking). The overhead of acquiring a lock is avoided when the instance is already initialized.
        if (instance == null) {
            //  Only one thread can execute the code within the block at any given time.
            synchronized (LazyDoubleLockSingleton.class) {
                //  Second check (with locking). Prevent the creation of multiple instances
                //  by ensuring that the instance is still null before creating a new one.
                if (instance == null) {
                    instance = new LazyDoubleLockSingleton();
                }
            }
        }
        return instance;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning of this object is not allowed.");
    }
}