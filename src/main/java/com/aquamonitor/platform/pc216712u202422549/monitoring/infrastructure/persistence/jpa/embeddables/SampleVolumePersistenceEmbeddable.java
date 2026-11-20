package com.aquamonitor.platform.pc216712u202422549.monitoring.infrastructure.persistence.jpa.embeddables;

import jakarta.persistence.Embeddable;

/**
 * Persistence representation of a sample volume.
 *
 * @author Jorge Mateo León Naupari
 */
@Embeddable
public class SampleVolumePersistenceEmbeddable {

    private Integer quantity;

    private String unit;

    public SampleVolumePersistenceEmbeddable() {
    }

    public SampleVolumePersistenceEmbeddable(Integer quantity, String unit) {
        this.quantity = quantity;
        this.unit = unit;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }
}
