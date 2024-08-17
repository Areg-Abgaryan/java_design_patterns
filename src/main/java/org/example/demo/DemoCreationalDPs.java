/**
 * Copyright (c) 2024 Areg Abgaryan
 */

package org.example.demo;

import org.example.design_patterns.creational.builder.Resource;
import org.example.design_patterns.creational.builder.ResourceImportance;
import org.example.design_patterns.creational.singleton.InnerClassSingleton;
import org.example.design_patterns.creational.singleton.LazyDoubleLockSingleton;

public class DemoCreationalDPs {

    public static void demo() {
        demoSingleton();
        demoBuilder();
    }

    private static void demoSingleton() {

        //  Singleton with Inner Class
        final InnerClassSingleton innerClassSingleton = InnerClassSingleton.getInstance();

        //  Singleton with Lazy initialization & Double Lock
        final LazyDoubleLockSingleton lazyDoubleLockSingleton = LazyDoubleLockSingleton.getInstance();
    }

    private static void demoBuilder() {

        //  Builder
        final Resource resource = new Resource.ResourceBuilder("vm1", "VM", 1024)
                .setDescription("test virtual machine")
                .setImportance(ResourceImportance.HIGH)
                .build();
    }
}
