/**
 * Copyright (c) 2024 Areg Abgaryan
 */

package org.example.creational.singleton;

import org.example.design_patterns.creational.singleton.LazyDoubleLockSingleton;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
final class LazyDoubleLockSingletonTest {

    //  Initialize singleton instance holder with atomic reference
    private final AtomicReference<LazyDoubleLockSingleton> instanceHolder = new AtomicReference<>();

    /**
     * Test to ensure only one instance is created for the Singleton class.
     */
    @Test
    @Order(1)
    void testSingleInstance() {
        final var instance1 = LazyDoubleLockSingleton.getInstance();
        final var instance2 = LazyDoubleLockSingleton.getInstance();
        Assertions.assertSame(instance1, instance2, "The instances must be the same.");
    }

    /**
     * Test to ensure that the Singleton is thread-safe.
     */
    @Test
    @Order(2)
    void testThreadSafety() throws InterruptedException {
        final int numberOfThreads = 100;
        final Thread[] threads = new Thread[numberOfThreads];

        //  Create and start multiple threads
        for (int i = 0; i < numberOfThreads; ++i) {
            threads[i] = new Thread(() -> {
                final var instance = LazyDoubleLockSingleton.getInstance();
                instanceHolder.updateAndGet(existingInstance -> {
                    if (existingInstance == null) {
                        //  First thread to store instance
                        return instance;
                    } else {
                        //  Subsequent threads should see the same instance
                        Assertions.assertSame(existingInstance, instance, "All instances should be the same.");
                        return existingInstance;
                    }
                });
            });
        }

        //  Start all threads
        Arrays.stream(threads).forEach(Thread::start);

        //  Wait for all threads to finish
        for (Thread thread : threads) {
            thread.join();
        }

        //  Verify that all threads got the same instance
        final LazyDoubleLockSingleton firstInstance = instanceHolder.get();
        Assertions.assertNotNull(firstInstance, "Instance must not be null.");
        for (int i = 0; i < numberOfThreads; ++i) {
            Assertions.assertSame(firstInstance, LazyDoubleLockSingleton.getInstance(),
                    "All instances must be the same.");
        }
    }

    /**
     * Test to ensure that cloning the Singleton instance is not supported.
     */
    @Test
    @Order(3)
    void testCloneNotSupported() {
        final var instance = LazyDoubleLockSingleton.getInstance();
        Assertions.assertThrows(CloneNotSupportedException.class, instance::clone, "Cloning must not be supported.");
    }
}