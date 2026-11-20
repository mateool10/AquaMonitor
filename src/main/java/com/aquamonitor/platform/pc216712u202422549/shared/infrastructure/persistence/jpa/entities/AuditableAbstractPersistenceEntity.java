package com.aquamonitor.platform.pc216712u202422549.shared.infrastructure.persistence.jpa.entities;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

/**
 * Base JPA persistence entity for all persistence entities that require auditing.
 *
 * <p>Provides {@code id}, {@code createdAt} and {@code updatedAt} fields, keeping
 * JPA and auditing concerns out of the domain model. When an entity is created,
 * both dates receive the same creation instant.</p>
 *
 * @author Jorge Mateo León Naupari
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableAbstractPersistenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
