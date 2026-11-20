package com.aquamonitor.platform.pc216712u202422549.monitoring.domain.model.valueobjects;

/**
 * Value object that represents the unique code of a sampling plan.
 *
 * @param code the plan code, for example SAM-001
 * @author Jorge Mateo León Naupari
 */
public record PlanCode(String code) {

    /**
     * Validates the plan code.
     *
     * @throws IllegalArgumentException if the code is null or blank
     */
    public PlanCode {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Plan code must not be null or blank");
        }
    }
}
