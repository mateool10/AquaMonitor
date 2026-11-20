package com.aquamonitor.platform.pc216712u202422549.monitoring.infrastructure.persistence.jpa.embeddables;

import jakarta.persistence.Embeddable;

import java.time.LocalDate;

/**
 * Persistence representation of a sampling window.
 *
 * @author Jorge Mateo León Naupari
 */
@Embeddable
public class SamplingWindowPersistenceEmbeddable {

    private LocalDate startDate;

    private LocalDate endDate;

    public SamplingWindowPersistenceEmbeddable() {
    }

    public SamplingWindowPersistenceEmbeddable(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
