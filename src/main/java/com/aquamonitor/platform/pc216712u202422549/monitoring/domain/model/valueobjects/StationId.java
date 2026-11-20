package com.aquamonitor.platform.pc216712u202422549.monitoring.domain.model.valueobjects;

import java.util.UUID;

/**
 * Value object that references a monitoring station managed by another bounded context.
 *
 * @param value the UUID of the monitoring station
 * @author Jorge Mateo León Naupari
 */
public record StationId(UUID value) {
    
    public StationId {
        if (value == null) {
            throw new IllegalArgumentException("Station id must not be null");
        }
    }
}
