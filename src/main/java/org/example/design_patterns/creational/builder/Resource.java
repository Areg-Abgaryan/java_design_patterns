/**
 * Copyright (c) 2024 Areg Abgaryan
 */

package org.example.design_patterns.creational.builder;

import lombok.Getter;
import org.example.utils.Utils;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
public final class Resource {

    //  Mandatory fields
    private final String name;
    private final String resourceKind;
    private final long size;

    //  Fields with default values during initialization
    private final ResourceStatus status;
    private final ResourceImportance importance;

    //  Auto-assigned fields
    private final UUID uuid;
    private final long creationDate;

    //  Optional fields
    private final String description;
    private final Set<UUID> ancestors;
    private final Set<UUID> descendants;
    private final long lastModifiedDate;

    Resource(ResourceBuilder builder) {
        this.name = builder.getName();
        this.resourceKind = builder.getResourceKind();
        this.size = builder.getSize();
        this.status = ResourceStatus.ACTIVE;
        this.importance = ResourceImportance.UNKNOWN;
        this.uuid = builder.getUuid();
        this.creationDate = builder.getCreationDate();
        this.description = builder.getDescription();
        this.ancestors = builder.getAncestors();
        this.descendants = builder.getDescendants();
        this.lastModifiedDate = builder.getLastModifiedDate();
    }


    //  Builder class
    @Getter
    public static final class ResourceBuilder {

        private final String name;
        private final String resourceKind;
        private final long size;

        private final UUID uuid;
        private final long creationDate;

        private ResourceStatus status;
        private ResourceImportance importance;

        private String description;
        private Set<UUID> ancestors;
        private Set<UUID> descendants;
        private long lastModifiedDate;

        public ResourceBuilder(String name, String resourceKind, long size) {
            validate(name, resourceKind, size);
            this.name = name;
            this.resourceKind = resourceKind;
            this.size = size;
            this.uuid = UUID.randomUUID();
            this.creationDate = Instant.now().toEpochMilli();
        }

        //  Builder copy constructor
        public ResourceBuilder(ResourceBuilder builder) {
            this.name = builder.getName();
            this.resourceKind = builder.getResourceKind();
            this.size = builder.getSize();
            this.status = builder.getStatus();
            this.importance = builder.getImportance();
            this.uuid = builder.getUuid();
            this.creationDate = builder.getCreationDate();
            this.description = builder.getDescription();
            this.ancestors = new HashSet<>(builder.getAncestors());
            this.descendants = new HashSet<>(builder.getDescendants());
            this.lastModifiedDate = builder.getLastModifiedDate();
        }

        public Resource build() {
            return new Resource(this);
        }


        //  Setter methods
        public ResourceBuilder setStatus(ResourceStatus status) {
            this.status = status;
            return this;
        }
        public ResourceBuilder setImportance(ResourceImportance importance) {
            this.importance = importance;
            return this;
        }
        public ResourceBuilder setDescription(String description) {
            this.description = description;
            return this;
        }
        public ResourceBuilder setAncestors(Set<UUID> ancestors) {
            this.ancestors = ancestors;
            return this;
        }
        public ResourceBuilder setDescendants(Set<UUID> descendants) {
            this.descendants = descendants;
            return this;
        }
        public ResourceBuilder setLastModifiedDate(long lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
            return this;
        }
    }


    private static void validate(String name, String resourceKind, long size) {
        if (Utils.isBlank(name)) {
            throw new IllegalArgumentException("Error: Resource name must not be null or empty");
        }

        if (Utils.isBlank(resourceKind)) {
            throw new IllegalArgumentException("Error: Resource kind must not be null or empty");
        }

        if (size <= 0) {
            throw new IllegalArgumentException("Error: Resource size must be positive");
        }
    }
}
