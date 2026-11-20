package com.aquamonitor.platform.pc216712u202422549.monitoring.domain.model.valueobjects;

/**
 * Value object that represents the volume of water to collect and its unit, for example 10 LITER.
 *
 * @param quantity the volume in whole units, it must be greater than zero
 * @param unit     the unit of measure
 * @author Jorge Mateo León Naupari
 */
public record SampleVolume(Integer quantity, String unit) {

    public SampleVolume {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Sample volume quantity must be greater than zero");
        }
        if (unit == null || unit.isBlank()) {
            throw new IllegalArgumentException("Sample volume unit must not be null or blank");
        }
    }

    public SampleVolume addVolume(Integer additionalVolume) {
        if (additionalVolume == null || additionalVolume <= 0) {
            throw new IllegalArgumentException("Additional volume must be greater than zero");
        }
        if (quantity > Integer.MAX_VALUE - additionalVolume) {
            throw new IllegalArgumentException("Sample volume quantity exceeds the maximum allowed value");
        }
        return new SampleVolume(quantity + additionalVolume, unit);
    }
}
