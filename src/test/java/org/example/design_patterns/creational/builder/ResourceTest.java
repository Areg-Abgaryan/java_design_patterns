/**
 * Copyright (c) 2024 Areg Abgaryan
 */

package org.example.design_patterns.creational.builder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

class ResourceTest {

    private static final long TEST_CREATION_DATE_MS = 1723551913018L;
    private static String name, resourceKind;
    private static long size;


    /**
     * Test to validate the Builder's mandatory fields.
     */
    @Test
    @Order(1)
    void testBuilderMandatoryFieldsValidity() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Resource.ResourceBuilder(null, "Data", 1024));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Resource.ResourceBuilder("  ", "Data", 1024));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Resource.ResourceBuilder("Test Name", null, 1024));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Resource.ResourceBuilder("Test Name", "", 1024));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Resource.ResourceBuilder("Test Name", "Data", 0));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Resource.ResourceBuilder("Test Name", "Data", -1024));
    }

    /**
     * Test the Builder's mandatory fields.
     */
    @Test
    @Order(2)
    void testBuilderMandatoryFields() {
        initialize("host1", "Host", 1024);
        final Resource resource = new Resource.ResourceBuilder(name, resourceKind, size).build();

        Assertions.assertEquals(name, resource.getName());
        Assertions.assertEquals(resourceKind, resource.getResourceKind());
        Assertions.assertEquals(size, resource.getSize());
    }

    /**
     * Test the Builder's default fields.
     */
    @Test
    @Order(3)
    void testBuilderDefaultFields() {
        initialize("cluster1", "Cluster", 2048);
        final Resource resource = new Resource.ResourceBuilder(name, resourceKind, size).build();

        Assertions.assertEquals(ResourceStatus.ACTIVE, resource.getStatus());
        Assertions.assertEquals(ResourceImportance.UNKNOWN, resource.getImportance());
    }

    /**
     * Test the Builder's optional fields.
     */
    @Test
    @Order(4)
    void testBuilderOptionalFields() {
        initialize("datacenter1", "Datacenter", 3072);

        final String description = "Test description";
        final Set<UUID> ancestors = new HashSet<>(Arrays.asList(UUID.randomUUID(), UUID.randomUUID()));

        final Resource resource = new Resource.ResourceBuilder(name, resourceKind, size)
                .setDescription(description)
                .setAncestors(ancestors)
                .build();

        Assertions.assertEquals(description, resource.getDescription());
        Assertions.assertEquals(ancestors, resource.getAncestors());
        Assertions.assertNull(resource.getDescendants());
        Assertions.assertEquals(0, resource.getLastModifiedDate());
    }

    /**
     * Test the Builder's auto-assigned fields.
     */
    @Test
    @Order(5)
    void testBuilderAutoAssignedFields() {
        initialize("vm1", "VM", 4096);
        final Resource resource = new Resource.ResourceBuilder(name, resourceKind, size).build();

        Assertions.assertNotNull(resource.getUuid());
        Assertions.assertTrue(resource.getCreationDate() > TEST_CREATION_DATE_MS);
    }

    /**
     * Test the Builder's mutability.
     */
    @Test
    @Order(6)
    void testMutability() {
        initialize("Test Resource", "Data", 1024);
        final var builder = new Resource.ResourceBuilder(name, resourceKind, size);
        builder.setDescription("Initial Description");

        final String newDescription = "Updated Description";
        builder.setDescription(newDescription);
        final Resource resource = builder.build();

        Assertions.assertEquals(newDescription, resource.getDescription());
    }

    /**
     * Test the Builder's copy constructor.
     */
    @Test
    @Order(7)
    void testCopyConstructor() {
        final var builder = new Resource.ResourceBuilder("Test Resource", "Data", 5000)
                .setStatus(ResourceStatus.INACTIVE)
                .setImportance(ResourceImportance.CRITICAL)
                .setDescription("Test Description")
                .setAncestors(new HashSet<>(Arrays.asList(UUID.randomUUID(), UUID.randomUUID())))
                .setDescendants(Collections.singleton(UUID.randomUUID()))
                .setLastModifiedDate(System.currentTimeMillis());

        final var copiedBuilder = new Resource.ResourceBuilder(builder);
        areEqual(builder, copiedBuilder);
    }


    private void initialize(String nameVar, String resourceKindVar, long sizeVar) {
        name = nameVar;
        resourceKind = resourceKindVar;
        size = sizeVar;
    }

    private static void areEqual(Resource.ResourceBuilder builder1, Resource.ResourceBuilder builder2) {
        //  Assert mandatory fields
        Assertions.assertEquals(builder1.getName(), builder2.getName());
        Assertions.assertEquals(builder1.getResourceKind(), builder2.getResourceKind());
        Assertions.assertEquals(builder1.getSize(), builder2.getSize());

        //  Assert default fields
        Assertions.assertEquals(builder1.getStatus(), builder2.getStatus());
        Assertions.assertEquals(builder1.getImportance(), builder2.getImportance());

        //  Assert optional fields
        Assertions.assertEquals(builder1.getDescription(), builder2.getDescription());
        Assertions.assertEquals(builder1.getAncestors(), builder2.getAncestors());
        Assertions.assertEquals(builder1.getDescendants(), builder2.getDescendants());
        Assertions.assertEquals(builder1.getLastModifiedDate(), builder2.getLastModifiedDate());

        //  Assert auto-assigned fields
        Assertions.assertNotNull(builder2.getUuid());
        Assertions.assertTrue(builder2.getCreationDate() > TEST_CREATION_DATE_MS);
    }
}

